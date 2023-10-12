import React, { Component } from "react"
import DepartmentService from "../services/DepartmentService"

class CreateOrEditDepartmentComponent extends Component {
	constructor(props) {
		super(props)

		this.state = {
			id: this.props.match.params.id,
			name: "",
		}

		this.cancel = this.cancel.bind(this)
	}

	componentDidMount() {
		if (this.state.id === "add") {
			return
		} else {
			DepartmentService.getDepartmentById(this.state.id).then(res => {
				let department = res.data
				this.setState({
					name: department.name,
				})
			})
		}
	}

	saveOrUpdateDepartment = e => {
		e.preventDefault()
		if (this.state.id === "add") {
			let department = {
				name: this.state.name,
			}
			DepartmentService.createDepartment(department)
				.catch(err => {
					alert(err.response.data)
				})
				.then(res => {
					this.props.history.push("/summary/department")
				})
		} else {
			let department = {
				id: this.state.id,
				name: this.state.name,
			}
			DepartmentService.updateDepartment(department)
				.then(res => {
					this.props.history.push("/summary/department")
				})
				.catch(err => {
					alert(err.response.data)
				})
		}
	}

	getTitle() {
		if (this.props.id != "add") {
			return <h4 style={{ textAlign: "center" }}> Редактирование отдела</h4>
		} else {
			return <h4 style={{ textAlign: "center" }}> Создание нового отдела</h4>
		}
	}

	cancel = () => {
		this.props.history.push("/summary/department")
	}

	render() {
		return (
			<div>
				<div>{this.getTitle()}</div>
				<div className='container'>
					<div className='card col-md-6 offset-md-3 offset-md-3'>
						<div className='card-body'>
							<form>
								<div className='form-group'>
									<label style={{ fontSize: 20 }}>Наименование: </label>
									<input
										name='departmentName'
										className='form-control'
										value={this.state.name}
										onChange={event => {
											this.setState({ name: event.target.value })
										}}
									/>
								</div>
								<div style={{ marginTop: "20px" }}>
									<button
										className='btn btn-success'
										onClick={this.saveOrUpdateDepartment}
									>
										Сохранить
									</button>
									<button
										className='btn btn-primary'
										onClick={this.cancel}
										style={{ marginLeft: "10px" }}
									>
										Назад
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		)
	}
}

export default CreateOrEditDepartmentComponent
