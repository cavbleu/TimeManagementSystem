import React, { Component } from "react"
import DepartmentService from "../services/DepartmentService"

class k extends Component {
	constructor(props) {
		super(props)

		this.state = {
			id: this.props.match.params.id,
			brand: "",
			model: "",
			category: "",
			gosNumber: "",
			productionYear: "",
			vehicleType: "",
		}
		this.cancel = this.cancel.bind(this)
	}

	changeBrandHandler = event => {
		this.setState({ brand: event.target.value })
	}
	changeModelHandler = event => {
		this.setState({ model: event.target.value })
	}
	changeCategoryHandler = event => {
		this.setState({ category: event.target.value })
	}
	changeGosNumberHandler = event => {
		this.setState({ gosNumber: event.target.value })
	}
	changeProductionYearHandler = event => {
		this.setState({ productionYear: event.target.value })
	}

	changeVehicleType = event => {
		this.setState({ vehicleType: event.target.value })
	}

	cancel() {
		this.props.history.push("/vehicles")
	}

	componentDidMount() {
		if (this.state.id === "_add") {
			return
		} else {
			DepartmentService.getVehicleById(this.state.id).then(res => {
				let vehicle = res.data
				this.setState({
					brand: vehicle.brand,
					model: vehicle.model,
					category: vehicle.category,
					gosNumber: vehicle.gosNumber,
					productionYear: vehicle.productionYear,
					vehicleType: vehicle.vehicleType,
				})
			})
		}
	}

	saveOrUpdateDepartment = e => {
		e.preventDefault()
		let vehicle = {
			brand: this.state.brand,
			model: this.state.model,
			category: this.state.category,
			gosNumber: this.state.gosNumber,
			productionYear: this.state.productionYear,
			vehicleType: this.state.vehicleType,
		}
		console.log("vehicle => " + JSON.stringify(vehicle))

		if (this.state.id === "_add") {
			VehicleService.createVehicle(vehicle)
				.catch(err => {
					console.log(err)
					if ((err.status = 404)) {
						alert("ТС с таким гос. номером уже существует в таблице")
					}
				})
				.then(res => {
					this.props.history.push("/vehicles")
				})
		} else {
			VehicleService.updateVehicle(vehicle, this.state.id).then(res => {
				this.props.history.push("/vehicles")
			})
		}
	}

	getTitle() {
		if (this.state.id === "_add") {
			return <h3 className='text-center'>Добавить ТС</h3>
		} else {
			return <h3 className='text-center'>Обновить ТС</h3>
		}
	}

	render() {
		return (
			<div>
				<div className='container'>
					<div className='card col-md-6 offset-md-3 offset-md-3'>
						{this.getTitle()}
						<div className='card-body'>
							<form>
								<div className='form-group'>
									<label>Марка: </label>
									<input
										placeholder='Марка'
										name='brand'
										className='form-control'
										value={this.state.brand}
										onChange={this.changeBrandHandler}
									/>
								</div>
								<div className='form-group'>
									<label>Модель: </label>
									<input
										placeholder='Модель'
										name='model'
										className='form-control'
										value={this.state.model}
										onChange={this.changeModelHandler}
									/>
								</div>
								<div className='form-group'>
									<label>Категория: </label>
									<input
										placeholder='Категория'
										name='category'
										className='form-control'
										value={this.state.category}
										onChange={this.changeCategoryHandler}
									/>
								</div>
								<div className='form-group'>
									<label>Гос. номер: </label>
									<input
										placeholder='Гос. номер'
										name='gosNumber'
										className='form-control'
										value={this.state.gosNumber}
										onChange={this.changeGosNumberHandler}
									/>
								</div>
								<div className='form-group'>
									<label>Год выпуска: </label>
									<input
										placeholder='Год выпуска'
										name='productionYear'
										className='form-control'
										value={this.state.productionYear}
										onChange={this.changeProductionYearHandler}
									/>
								</div>
								<div style={{ marginTop: "5px" }}>
									<label htmlFor='dog-names'>Тип ТС:</label>
									<select
										style={{ marginLeft: "3px" }}
										onSubmit={this.changeVehicleType}
									>
										<option>{this.state.vehicleType}</option>
										<option value='Атомобиль'>Атомобиль</option>
										<option value='Мотоцикл'>Мотоцикл</option>
										<option value='Прицеп'>Прицеп</option>
										<option value='Тягач'>Тягач</option>
									</select>
								</div>
								<div style={{ marginLeft: "250px" }}>
									<button
										style={{ marginTop: "5px" }}
										className='btn btn-success'
										onClick={this.saveOrUpdateDepartment}
									>
										Сохранить
									</button>
									<button
										className='btn btn-primary'
										onClick={this.cancel}
										style={{ marginLeft: "10px", marginTop: "5px" }}
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

export default k
