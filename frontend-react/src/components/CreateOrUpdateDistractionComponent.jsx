import React, { Component } from "react"
import DepartmentService from "../services/DepartmentService"
import DistractionService from "../services/DistractionService"
import EmployeeService from "../services/EmployeeService"
import PositionService from "../services/PositionService"

class CreateOrUpdateDistractionComponent extends Component {
	constructor(props) {
		super(props)

		this.state = {
			id: this.props.match.params.id,
			empId: "",
			name: "",
			employee: {
				id: "",
				name: "",
				age: "",
				position: {
					id: "",
					name: "",
					department: {
						id: "",
						name: "",
					},
				},
			},
			position: {
				id: "",
				name: "",
				department: {
					id: "",
					name: "",
				},
			},
			date: "",
			absenceReason: "",
			startWork: "",
			endWork: "",

			allDepartments: [
				{
					id: "",
					name: "",
				},
			],

			allPositions: [
				{
					id: "",
					name: "",
					department: {
						id: "",
						name: "",
					},
				},
			],

			allEmployees: [
				{
					id: "",
					name: "",
					position: {
						id: "",
						name: "",
						department: {
							id: "",
							name: "",
						},
					},
				},
			],
		}

		this.cancel = this.cancel.bind(this)
	}

	componentDidMount() {
		if (this.state.id === "add") {
			EmployeeService.getAll().then(res => {
				this.setState({
					allEmployees: res.data,
				})
			})

			PositionService.getAll().then(res => {
				this.setState({
					allPositions: res.data,
				})
			})

			DepartmentService.getAll().then(res => {
				this.setState({
					allDepartments: res.data,
				})
			})
		} else {
			DistractionService.getById(this.state.id).then(res => {
				let timeSheet = res.data
				this.setState({
					id: timeSheet.id,
					date: timeSheet.date,
					startWork: timeSheet.startDistraction,
					endWork: timeSheet.endDistraction,
					employee: timeSheet.employee,
					position: timeSheet.employee.position,
					name: timeSheet.employee.name,
				})
			})
		}
	}

	saveOrUpdate = e => {
		e.preventDefault()
		if (this.state.id === "add") {
			let distraction = {
				employee: {
					id: this.state.empId,
					name: this.state.name,
					position: this.state.position,
				},
				date: this.state.date,
				startDistraction: this.state.startWork,
				endDistraction: this.state.endWork,
			}
			console.log(JSON.stringify(distraction))
			DistractionService.create(distraction)
				.then(res => {
					this.props.history.push("/summary/distraction")
				})
				.catch(err => {
					let r = err.response.data
					if (r.status == 500) {
						alert(
							"Время должно быть указано в формате чч:мм. Дата должно быть указана в формате дд-мм-гггг. Все поля должны быть заполнены корректными значениями"
						)
					} else if (r.type === "about:blank") {
						alert(
							"Время должно быть указано в формате чч:мм. Дата должно быть указана в формате дд-мм-гггг. Все поля должны быть заполнены корректными значениями"
						)
					} else {
						alert(
							r.message +
								". Statuscode: " +
								r.statusCode +
								". Status: " +
								r.status +
								". Timestamp: " +
								r.timestamp
						)
					}
				})
		} else {
			let timeSheet = {
				id: this.state.id,
				date: this.state.date,
				startDistraction: this.state.startWork,
				endDistraction: this.state.endWork,
				employee: this.state.employee,
			}
			console.log(JSON.stringify(timeSheet))
			DistractionService.update(timeSheet)
				.then(res => {
					this.props.history.push("/summary/distraction")
				})
				.catch(err => {
					let r = err.response.data
					if (r.status == 500) {
						alert(
							"Время должно быть указано в формате чч:мм. Дата должно быть указана в формате дд-мм-гггг. Все поля должны быть заполнены корректными значениями"
						)
					} else if (r.type === "about:blank") {
						alert(
							"Время должно быть указано в формате чч:мм. Дата должно быть указана в формате дд-мм-гггг. Все поля должны быть заполнены корректными значениями"
						)
					} else {
						alert(
							r.message +
								". Statuscode: " +
								r.statusCode +
								". Status: " +
								r.status +
								". Timestamp: " +
								r.timestamp
						)
					}
				})
		}
	}

	getTitle() {
		if (this.state.id != "add") {
			return (
				<h3 style={{ textAlign: "center" }}>
					Редактирование табель учета времени отвлечений
				</h3>
			)
		} else {
			return (
				<h3 style={{ textAlign: "center" }}>
					Добавление нового табеля учета времени отвлечений
				</h3>
			)
		}
	}

	cancel = () => {
		this.props.history.push("/summary/distraction")
	}

	render() {
		let optionTemplateEmployees = this.state.allEmployees.map(emp => (
			<div>
				<li>
					<button
						class='dropdown-item'
						type='button'
						onClick={() => {
							this.setState({
								empId: emp.id,
								name: emp.name,

								position: {
									id: emp.position.id,
									name: emp.position.name,
									department: {
										id: emp.position.department.id,
										name: emp.position.department.name,
									},
								},
							})
							this.state.empId = emp.id
							this.state.name = emp.name
						}}
					>
						{emp.name}
					</button>
				</li>
				<li>
					<hr class='dropdown-divider' />
				</li>
			</div>
		))

		let dropdown
		if (this.state.id === "add") {
			dropdown = (
				<div>
					<div style={{ marginTop: 20 }}>
						<label style={{ fontSize: 20 }}>Выберите сотрудника</label>
					</div>

					<div className='dropdown' style={{ marginTop: 10 }}>
						<button
							type='button'
							class='btn btn-secondary dropdown-toggle'
							data-bs-toggle='dropdown'
							data-bs-display='static'
							aria-expanded='false'
						>
							{this.state.name}
						</button>
						<ul class='dropdown-menu dropdown-menu-end dropdown-menu-lg-start'>
							{optionTemplateEmployees}
						</ul>
					</div>
				</div>
			)
		} else {
			dropdown = (
				<div style={{ marginTop: 20 }}>
					<label style={{ fontSize: 20 }}>Сотрудник: </label>
					<input
						style={{ marginTop: 10 }}
						className='form-control'
						placeholder={this.state.name}
						disabled
					/>
				</div>
			)
		}

		return (
			<div>
				<div style={{ marginTop: 20 }}>{this.getTitle()}</div>
				<div className='container'>
					<div className='card col-md-6 offset-md-3 offset-md-3'>
						<div className='card-body'>
							<form>
								<div>
									<label style={{ fontSize: 20 }}>
										Дата в формате "дд-мм-гггг":{" "}
									</label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.date}
										onChange={event => {
											this.setState({ date: event.target.value })
										}}
									/>
								</div>
								<div>
									<label style={{ fontSize: 20, marginTop: 20 }}>
										Время начала в формате "чч:мм":
									</label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.startWork}
										onChange={event => {
											this.setState({ startWork: event.target.value })
										}}
									/>
								</div>
								<div>
									<label style={{ fontSize: 20, marginTop: 20 }}>
										Время окончания в формате "чч:мм":
									</label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.endWork}
										onChange={event => {
											this.setState({ endWork: event.target.value })
										}}
									/>
								</div>

								{dropdown}

								<div style={{ marginTop: 20 }}>
									<label style={{ fontSize: 20 }}>Должность: </label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										placeholder={this.state.position.name}
										disabled
									/>
								</div>
								<div style={{ marginTop: 20 }}>
									<label style={{ fontSize: 20 }}>Отдел: </label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										placeholder={this.state.position.department.name}
										disabled
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

export default CreateOrUpdateDistractionComponent
