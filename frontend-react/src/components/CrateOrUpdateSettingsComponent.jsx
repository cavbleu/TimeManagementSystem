import React, { Component } from "react"
import SettingsService from "../services/SettingsService"

class CreateOrUpdateSettingsComponent extends Component {
	constructor(props) {
		super(props)

		this.state = {
			id: this.props.match.params.id,
			name: "",
			currentSettingsProfile: "",
			defaultWorkTime: "",
			defaultStartWork: "",
			maxLateCountByMonth: "",
			maxEarlyLivingCountByMonth: "",
			maxAbsenceCountByMonth: "",
			maxSkipCountByMonth: "",
			maxDistractionTimeByDay: "",
			maxRestTimeByDay: "",
			maxExcessDistractionCountByMonth: "",
			maxExcessRestCountByMonth: "",
		}
		this.cancel = this.cancel.bind(this)
	}

	componentDidMount() {
		if (this.state.id === "add") {
		} else {
			SettingsService.getById(this.state.id).then(res => {
				let settings = res.data
				this.setState({
					name: settings.name,
					currentSettingsProfile: settings.currentSettingsProfile,
					defaultWorkTime: settings.defaultWorkTime,
					defaultStartWork: settings.defaultStartWork,
					maxLateCountByMonth: settings.maxLateCountByMonth,
					maxEarlyLivingCountByMonth: settings.maxEarlyLivingCountByMonth,
					maxAbsenceCountByMonth: settings.maxAbsenceCountByMonth,
					maxSkipCountByMonth: settings.maxAbsenceCountByMonth,
					maxDistractionTimeByDay: settings.maxDistractionTimeByDay,
					maxRestTimeByDay: settings.maxRestTimeByDay,
					maxExcessDistractionCountByMonth:
						settings.maxExcessDistractionCountByMonth,
					maxExcessRestCountByMonth: settings.maxExcessRestCountByMonth,
				})
			})
		}
	}

	saveOrUpdate = e => {
		e.preventDefault()
		if (this.state.id === "add") {
			let settings = {
				name: this.state.name,
				currentSettingsProfile: this.state.currentSettingsProfile,
				defaultWorkTime: this.state.defaultWorkTime,
				defaultStartWork: this.state.defaultStartWork,
				maxLateCountByMonth: this.state.maxLateCountByMonth,
				maxEarlyLivingCountByMonth: this.state.maxEarlyLivingCountByMonth,
				maxAbsenceCountByMonth: this.state.maxAbsenceCountByMonth,
				maxSkipCountByMonth: this.state.maxSkipCountByMonth,
				maxDistractionTimeByDay: this.state.maxDistractionTimeByDay,
				maxRestTimeByDay: this.state.maxRestTimeByDay,
				maxExcessDistractionCountByMonth:
					this.state.maxExcessDistractionCountByMonth,
				maxExcessRestCountByMonth: this.state.maxExcessRestCountByMonth,
			}
			console.log(JSON.stringify(settings))
			SettingsService.create(settings)
				.then(res => {
					this.props.history.push("/settings/limits")
				})
				.catch(err => {
					let r = err.response.data
					alert(
						r.message +
							". Statuscode: " +
							r.statusCode +
							". Status: " +
							r.status +
							". Timestamp: " +
							r.timestamp
					)
				})
		} else {
			let settings = {
				id: this.state.id,
				name: this.state.name,
				currentSettingsProfile: this.state.currentSettingsProfile,
				defaultWorkTime: this.state.defaultWorkTime,
				defaultStartWork: this.state.defaultStartWork,
				maxLateCountByMonth: this.state.maxLateCountByMonth,
				maxEarlyLivingCountByMonth: this.state.maxEarlyLivingCountByMonth,
				maxAbsenceCountByMonth: this.state.maxAbsenceCountByMonth,
				maxSkipCountByMonth: this.state.maxSkipCountByMonth,
				maxDistractionTimeByDay: this.state.maxDistractionTimeByDay,
				maxRestTimeByDay: this.state.maxRestTimeByDay,
				maxExcessDistractionCountByMonth:
					this.state.maxExcessDistractionCountByMonth,
				maxExcessRestCountByMonth: this.state.maxExcessRestCountByMonth,
			}
			SettingsService.update(settings)
				.then(res => {
					this.props.history.push("/settings/limits")
				})
				.catch(err => {
					let r = err.response.data
					alert(
						r.message +
							". Statuscode: " +
							r.statusCode +
							". Status: " +
							r.status +
							". Timestamp: " +
							r.timestamp
					)
				})
		}
	}

	getTitle() {
		if (this.state.id != "add") {
			return (
				<h3 style={{ textAlign: "center" }}>Редактирование профиля настроек</h3>
			)
		} else {
			return (
				<h3 style={{ textAlign: "center" }}>
					Добавление нового профиля настроек
				</h3>
			)
		}
	}

	cancel = () => {
		this.props.history.push("/settings/limits")
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
									<label style={{ fontSize: 16 }}>Наименование профиля:</label>
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
									<label style={{ fontSize: 16, marginTop: 20 }}>
										Норма рабочего времени за день:
									</label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.defaultWorkTime}
										onChange={event => {
											this.setState({ defaultWorkTime: event.target.value })
										}}
									/>
								</div>
								<div>
									<label style={{ fontSize: 16, marginTop: 20 }}>
										Норма начала рабочего дня в формате "чч:мм":
									</label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.defaultStartWork}
										onChange={event => {
											this.setState({
												defaultStartWork: event.target.value,
											})
										}}
									/>
								</div>
								<div>
									<label style={{ fontSize: 16, marginTop: 20 }}>
										Максимальное количество опозданий в месяц:
									</label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.maxLateCountByMonth}
										onChange={event => {
											this.setState({ maxLateCountByMonth: event.target.value })
										}}
									/>
								</div>

								<div style={{ marginTop: 20 }}>
									<label style={{ fontSize: 16 }}>
										Максимальное количество уходов с работы до истечения нормы
										рабочего времени:{" "}
									</label>
									<input
										style={{ marginTop: 20 }}
										className='form-control'
										value={this.state.maxEarlyLivingCountByMonth}
										onChange={event => {
											this.setState({
												maxEarlyLivingCountByMonth: event.target.value,
											})
										}}
									/>
								</div>
								<div style={{ marginTop: 20 }}>
									<label style={{ fontSize: 16 }}>
										Максимальное количество отсутствий за месяц:{" "}
									</label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.maxAbsenceCountByMonth}
										onChange={event => {
											this.setState({
												maxAbsenceCountByMonth: event.target.value,
											})
										}}
									/>
								</div>
								<div style={{ marginTop: 20 }}>
									<label style={{ fontSize: 16 }}>
										Максимальное количество прогулов за месяц:{" "}
									</label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.maxSkipCountByMonth}
										onChange={event => {
											this.setState({ maxSkipCountByMonth: event.target.value })
										}}
									/>
								</div>
								<div style={{ marginTop: 20 }}>
									<label style={{ fontSize: 16 }}>
										Максимальное суммарное время отвлечений в день:{" "}
									</label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.maxDistractionTimeByDay}
										onChange={event => {
											this.setState({
												maxDistractionTimeByDay: event.target.value,
											})
										}}
									/>
								</div>
								<div style={{ marginTop: 20 }}>
									<label style={{ fontSize: 16 }}>
										Максимальное суммарное время перерывов в день:{" "}
									</label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.maxRestTimeByDay}
										onChange={event => {
											this.setState({ maxRestTimeByDay: event.target.value })
										}}
									/>
								</div>
								<div style={{ marginTop: 20 }}>
									<label style={{ fontSize: 16 }}>
										Максимальное количество превышений времени перерывов в
										месяц:{" "}
									</label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.maxExcessRestCountByMonth}
										onChange={event => {
											this.setState({
												maxExcessRestCountByMonth: event.target.value,
											})
										}}
									/>
								</div>
								<div style={{ marginTop: 20 }}>
									<label style={{ fontSize: 16 }}>
										Максимальное количество превышений времени отвлечений в
										месяц:{" "}
									</label>
									<input
										style={{ marginTop: 10 }}
										className='form-control'
										value={this.state.maxExcessDistractionCountByMonth}
										onChange={event => {
											this.setState({
												maxExcessDistractionCountByMonth: event.target.value,
											})
										}}
									/>
								</div>
								<div>
									<div style={{ marginTop: 20 }}>
										<label style={{ fontSize: 16 }}>
											Сделать профиль активным:
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
											{this.state.currentSettingsProfile}
										</button>
										<ul class='dropdown-menu dropdown-menu-end dropdown-menu-lg-start'>
											<li>
												<button
													class='dropdown-item'
													type='button'
													onClick={() => {
														this.setState({
															currentSettingsProfile: "true",
														})
														this.state.currentSettingsProfile = "true"
													}}
												>
													true
												</button>
											</li>
											<li>
												<hr class='dropdown-divider' />
											</li>
											<li>
												<button
													class='dropdown-item'
													type='button'
													onClick={() => {
														this.setState({
															currentSettingsProfile: "false",
														})
														this.state.currentSettingsProfile = "false"
													}}
												>
													false
												</button>
											</li>
										</ul>
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

export default CreateOrUpdateSettingsComponent
