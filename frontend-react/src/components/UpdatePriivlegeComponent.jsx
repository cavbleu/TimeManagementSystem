import React, { Component } from "react"
import PrivilegeService from "../services/PrivilegeService"

class UpdatePrivilegeComponent extends Component {
	constructor(props) {
		super(props)

		this.state = {
			id: this.props.match.params.id,
			name: "",
			increasedAmount: "",
		}
	}

	componentDidMount() {
		PrivilegeService.getById(this.state.id).then(res => {
			let privilege = res.data
			this.setState({
				name: privilege.name,
				increasedAmount: privilege.increasedAmount,
			})
		})
	}

	saveOrUpdate = e => {
		e.preventDefault()

		let privilege = {
			id: this.state.id,
			name: this.state.name,
			increasedAmount: this.state.increasedAmount,
		}
		PrivilegeService.update(privilege)
			.then(res => {
				this.props.history.push("/settings/privilege")
			})
			.catch(err => {
				alert(err.response.data)
			})
	}

	getTitle() {
		return <h3 style={{ textAlign: "center" }}>Редактирование привилегии</h3>
	}

	cancel = () => {
		this.props.history.push("/settings/privilege")
	}

	render() {
		return (
			<div>
				<div style={{ marginTop: 20 }}>{this.getTitle()}</div>
				<div className='container'>
					<div className='card col-md-6 offset-md-3 offset-md-3'>
						<div className='card-body'>
							<form>
								<div>
									<label style={{ fontSize: 16 }}>{this.state.name}</label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.increasedAmount}
										onChange={event => {
											this.setState({ increasedAmount: event.target.value })
										}}
									/>
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

export default UpdatePrivilegeComponent
