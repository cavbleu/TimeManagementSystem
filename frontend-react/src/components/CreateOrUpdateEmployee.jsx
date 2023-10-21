import React, { Component } from "react"
import EmployeeService from "../services/EmployeeService"
import PositionService from "../services/PositionService"

class CreateOrUpdateEmployeeComponent extends Component {
	constructor(props) {
		super(props)

		this.state = {
			id: this.props.match.params.id,
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
			lateIncreased: false,
			earlyLeavingIncreased: false,
			absenceIncreased: false,
			skipIncreased: false,
			restTimeIncreased: false,
			lunchTimeIncreased: false,
			distractionTimeIncreased: false,
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
		}

		this.cancel = this.cancel.bind(this)
	}

	componentDidMount() {
		if (this.state.id === "add") {
			PositionService.getAll().then(res => {
				this.setState({
					allPositions: res.data,
				})
			})
		} else {
			EmployeeService.getById(this.state.id).then(res => {
				let employee = res.data
				this.setState({
					name: employee.name,
					age: employee.age,
					position: employee.position,
					lateIncreased: employee.lateIncreased,
					earlyLeavingIncreased: employee.earlyLeavingIncreased,
					absenceIncreased: employee.absenceIncreased,
					skipIncreased: employee.skipIncreased,
					restTimeIncreased: employee.restTimeIncreased,
					lunchTimeIncreased: employee.lunchTimeIncreased,
					distractionTimeIncreased: employee.distractionTimeIncreased,
				})
			})
			PositionService.getAll().then(res => {
				this.setState({
					allPositions: res.data,
				})
			})
		}
	}

	saveOrUpdate = e => {
		e.preventDefault()
		if (this.state.id === "add") {
			let employee = {
				name: this.state.name,
				age: this.state.age,
				position: this.state.position,
				lateIncreased: this.state.lateIncreased,
				earlyLeavingIncreased: this.state.earlyLeavingIncreased,
				absenceIncreased: this.state.absenceIncreased,
				skipIncreased: this.state.skipIncreased,
				restTimeIncreased: this.state.restTimeIncreased,
				lunchTimeIncreased: this.state.lunchTimeIncreased,
				distractionTimeIncreased: this.state.distractionTimeIncreased,
			}
			EmployeeService.create(employee)
				.then(res => {
					this.props.history.push("/summary/employee")
				})
				.catch(err => {
					alert(err.response.data)
				})
		} else {
			let employee = {
				id: this.state.id,
				name: this.state.name,
				position: this.state.position,
				lateIncreased: this.state.lateIncreased,
				earlyLeavingIncreased: this.state.earlyLeavingIncreased,
				absenceIncreased: this.state.absenceIncreased,
				skipIncreased: this.state.skipIncreased,
				restTimeIncreased: this.state.restTimeIncreased,
				lunchTimeIncreased: this.state.lunchTimeIncreased,
				distractionTimeIncreased: this.state.distractionTimeIncreased,
			}
			EmployeeService.update(employee)
				.then(res => {
					this.props.history.push("/summary/employee")
				})
				.catch(err => {
					alert(err.response.data)
				})
		}
	}

	getTitle() {
		if (this.props.id != "add") {
			return (
				<h3 style={{ textAlign: "center" }}> Добавление нового сотрудника</h3>
			)
		} else {
			return (
				<h3 style={{ textAlign: "center" }}>
					{" "}
					Редактирование данных сотрудника
				</h3>
			)
		}
	}

	cancel = () => {
		this.props.history.push("/summary/employee")
	}

	render() {
		let optionTemplate = this.state.allPositions.map(pos => (
			<div>
				<li>
					<button
						class='dropdown-item'
						type='button'
						onClick={() => {
							this.setState({
								position: {
									id: pos.id,
									name: pos.name,
									department: {
										id: pos.department.id,
										name: pos.department.name,
									},
								},
							})
							this.state.position.id = pos.id
							this.state.position.name = pos.name
						}}
					>
						{pos.name}
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
									<label style={{ fontSize: 20 }}>Имя сотрудника: </label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.name}
										onChange={event => {
											this.setState({ name: event.target.value })
										}}
									/>
								</div>

								<div>
									<label style={{ fontSize: 20, marginTop: 20 }}>
										Возраста сотрудника:
									</label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.age}
										onChange={event => {
											this.setState({ age: event.target.value })
										}}
									/>
								</div>

								<div style={{ marginTop: 20 }}>
									<label style={{ fontSize: 20 }}>
										Выберите назначаемую должность
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
										{this.state.position.name}
									</button>
									<ul class='dropdown-menu dropdown-menu-end dropdown-menu-lg-start'>
										{optionTemplate}
									</ul>
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

								<div style={{ marginTop: 20 }}>
									<label style={{ fontSize: 20 }}>Привилегии: </label>
									<div class='form-check form-switch' style={{ marginTop: 10 }}>
										<input
											class='form-check-input'
											type='checkbox'
											defaultChecked={this.state.lateIncreased}
											onChange={() =>
												this.setState({
													lateIncreased: true,
												})
											}
										/>
										<label
											class='form-check-label'
											for='flexSwitchCheckDefault'
										>
											Увеличенное количество опозданий
										</label>
									</div>
									<div class='form-check form-switch' style={{ marginTop: 10 }}>
										<input
											class='form-check-input'
											type='checkbox'
											defaultChecked={this.state.earlyLeavingIncreased}
											onChange={() =>
												this.setState({
													earlyLeavingIncreased: true,
												})
											}
										/>
										<label
											class='form-check-label'
											for='flexSwitchCheckDefault'
										>
											Увеличенное количество ранних уходов с работы
										</label>
									</div>
									<div class='form-check form-switch' style={{ marginTop: 10 }}>
										<input
											class='form-check-input'
											type='checkbox'
											defaultChecked={this.state.absenceIncreased}
											onChange={() =>
												this.setState({
													absenceIncreased: true,
												})
											}
										/>
										<label
											class='form-check-label'
											for='flexSwitchCheckDefault'
										>
											Увеличенное количество отсутствий на работе
										</label>
									</div>
									<div class='form-check form-switch' style={{ marginTop: 10 }}>
										<input
											class='form-check-input'
											type='checkbox'
											defaultChecked={this.state.skipIncreased}
											onChange={() =>
												this.setState({
													skipIncreased: true,
												})
											}
										/>
										<label
											class='form-check-label'
											for='flexSwitchCheckDefault'
										>
											Увеличенное количество прогулов работы
										</label>
									</div>
									<div class='form-check form-switch' style={{ marginTop: 10 }}>
										<input
											class='form-check-input'
											type='checkbox'
											defaultChecked={this.state.restTimeIncreased}
											onChange={() =>
												this.setState({
													restTimeIncreased: true,
												})
											}
										/>
										<label
											class='form-check-label'
											for='flexSwitchCheckDefault'
										>
											Увеличенное суммарное время перерывов
										</label>
									</div>
									<div class='form-check form-switch' style={{ marginTop: 10 }}>
										<input
											class='form-check-input'
											type='checkbox'
											defaultChecked={this.state.distractionTimeIncreased}
											onChange={() =>
												this.setState({
													distractionTimeIncreased: true,
												})
											}
										/>
										<label
											class='form-check-label'
											for='flexSwitchCheckDefault'
										>
											Увеличенное суммарное время отвлечений
										</label>
									</div>
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

export default CreateOrUpdateEmployeeComponent
