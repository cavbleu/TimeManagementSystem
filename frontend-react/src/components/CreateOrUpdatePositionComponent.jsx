import React, { Component } from "react"
import DepartmentService from "../services/DepartmentService"
import PositionService from "../services/PositionService"

class CreateOrUpdatePositionComponent extends Component {
	constructor(props) {
		super(props)

		this.state = {
			id: this.props.match.params.id,
			name: "",
			department: {
				id: "",
				name: "",
			},
			allDepartments: [
				{
					id: "",
					name: "",
				},
			],
		}

		this.cancel = this.cancel.bind(this)
	}

	componentDidMount() {
		if (this.state.id === "add") {
			DepartmentService.getAll().then(res => {
				this.setState({
					allDepartments: res.data,
				})
			})
		} else {
			PositionService.getById(this.state.id).then(res => {
				let position = res.data
				this.setState({
					name: position.name,
					department: position.department,
				})
			})
			DepartmentService.getAll().then(res => {
				this.setState({
					allDepartments: res.data,
				})
			})
		}
	}

	saveOrUpdate = e => {
		e.preventDefault()
		if (this.state.id === "add") {
			let position = {
				name: this.state.name,
				department: this.state.department,
			}
			PositionService.create(position)
				.catch(err => {
					alert(err.response.data)
				})
				.then(res => {
					this.props.history.push("/summary/position")
				})
		} else {
			let position = {
				id: this.state.id,
				name: this.state.name,
				department: this.state.department,
			}
			PositionService.update(position)
				.then(res => {
					this.props.history.push("/summary/position")
				})
				.catch(err => {
					alert(err.response.data)
				})
		}
	}

	getTitle() {
		if (this.props.id != "add") {
			return <h4 style={{ textAlign: "center" }}> Редактирование должности</h4>
		} else {
			return (
				<h4 style={{ textAlign: "center" }}> Добавление новой должности</h4>
			)
		}
	}

	cancel = () => {
		this.props.history.push("/summary/position")
	}

	render() {
		let optionTemplate = this.state.allDepartments.map(dep => (
			<div>
				<li>
					<button
						class='dropdown-item'
						type='button'
						onClick={() => {
							this.setState({
								department: {
									id: dep.id,
									name: dep.name,
								},
							})
							this.state.department.id = dep.id
							this.state.department.name = dep.name
						}}
					>
						{dep.name}
					</button>
				</li>
				<li>
					<hr class='dropdown-divider' />
				</li>
			</div>
		))

		return (
			<div>
				<div style={{ marginTop: 20 }}>{this.getTitle()}</div>
				<div className='container'>
					<div className='card col-md-6 offset-md-3 offset-md-3'>
						<div className='card-body'>
							<form>
								<div>
									<label style={{ fontSize: 20 }}>Наименование: </label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.name}
										onChange={event => {
											this.setState({ name: event.target.value })
										}}
									/>
								</div>

								<div style={{ marginTop: 20, fontSize: 13 }}>
									<label style={{ fontSize: 20 }}>
										Выберите отдел перевода
									</label>
								</div>

								<div className='dropdown' style={{ marginTop: 10 }}>
									<button
										type='button'
										class='btn btn-secondary dropdown-toggle'
										data-bs-toggle='dropdown'
										data-bs-display='static'
										aria-expanded='false'
									>
										{this.state.department.name}
									</button>
									<ul class='dropdown-menu dropdown-menu-end dropdown-menu-lg-start'>
										{optionTemplate}
									</ul>
								</div>
								<div
									className='mb-3'
									style={{ marginTop: "20px", marginLeft: 400 }}
								>
									<button
										className='btn btn-success'
										onClick={this.saveOrUpdate}
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

export default CreateOrUpdatePositionComponent
