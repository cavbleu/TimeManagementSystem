import { ru } from "date-fns/locale"
import moment from "moment"
import React, { Component } from "react"
import BootStrapTable from "react-bootstrap-table-next"
import filterFactory, {
	Comparator,
	numberFilter,
	textFilter,
} from "react-bootstrap-table2-filter"
import DatePicker from "react-datepicker"
import EmployeeService from "../services/EmployeeService"
import RestService from "../services/RestService"

class Helper {
	constructor(name, position, rest, id) {
		this.name = name
		this.position = position
		this.rest = rest
		this.id = id
	}
}

class RestSummaryComponent extends Component {
	constructor(props) {
		super(props)

		this.state = {
			employees: [],
			id: "",
			name: "",
			rest: {
				id: "",
				date: "",
				startRest: "",
				endRest: "",
				restTime: "",
			},

			position: {
				id: "",
				name: "",
				department: {
					id: "",
					name: "",
				},
			},
			startDate: new Date(2023, 9, 1),
			endDate: new Date(2023, 9, 31),
			options: {
				weekday: "long",
				year: "numeric",
				month: "long",
				day: "numeric",
			},
		}
	}

	componentDidMount() {
		let filterDto = {
			startDate: this.state.startDate.toLocaleDateString("ru-RU"),
			endDate: this.state.endDate.toLocaleDateString("ru-RU"),
		}
		console.log(JSON.stringify(filterDto))
		EmployeeService.getAllByPeriod(filterDto)
			.then(res => {
				let ar = []
				res.data.map(data => {
					data.rests.map(rest => {
						ar.push(new Helper(data.name, data.position, rest, data.id))
					})
				})

				ar.sort(
					(a, b) =>
						moment(b.rest.date, "DD.MM.YY") - moment(a.rest.date, "DD.MM.YY")
				)

				this.setState({
					employees: ar,
				})
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

	getEmployeeSummaryByPeriod = e => {
		e.preventDefault()

		if (this.state.startDate === null) {
			alert("Дата начала отчетного периода не может пустой")
		} else if (this.state.endDate === null) {
			alert("Дата окончания отчетного периода не может пустой")
		} else {
			let filterDto = {
				startDate: this.state.startDate.toLocaleDateString("ru-RU"),
				endDate: this.state.endDate.toLocaleDateString("ru-RU"),
			}

			EmployeeService.getAllByPeriod(filterDto)
				.then(res => {
					this.componentDidMount()
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

	columns = [
		{
			dataField: "rest.date",
			text: "Дата",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "name",
			text: "Имя",
			sort: true,
			filter: textFilter({
				placeholder: "Фильтр...",
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "position.name",
			text: "Должность",
			sort: true,
			filter: textFilter({
				placeholder: "Фильтр...",
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "position.department.name",
			text: "Отдел",
			sort: true,
			filter: textFilter({
				placeholder: "Фильтр...",
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "rest.startRest",
			text: "Время начала перерыва",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "rest.endRest",
			text: "Время окончания перерыва",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "rest.restTime",
			text: "Суммарное время, мин",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{ text: "Действия", formatter: this.buttonFormatter },
	]

	filterFormatter(column, colIndex, { sortElement, filterElement }) {
		return (
			<div style={{ display: "flex", flexDirection: "column" }}>
				{filterElement}
				{column.text}
				{sortElement}
			</div>
		)
	}

	buttonFormatter(cell, row, rowIndex) {
		return (
			<div>
				<button
					onClick={() => (window.location.href = `/add-rest/${row.rest.id}`)}
					className='btn btn-success btn-sm'
					style={{ fontSize: 13 }}
				>
					Редактировать
				</button>

				<button
					style={{ marginTop: 5, fontSize: 13 }}
					onClick={() => {
						RestService.delete(row.rest.id)
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
							.then(res => {
								window.location.reload()
							})
					}}
					className='btn btn-danger btn-sm'
				>
					Удалить
				</button>
			</div>
		)
	}

	render() {
		return (
			<div style={{ fontSize: 13 }}>
				<h2 className='text-center' style={{ marginTop: 20 }}>
					Сводная таблица перерывов сотрудников
				</h2>
				<div style={{ marginTop: 20 }}>
					<h5>Дата начала отчетного периода: </h5>
					<DatePicker
						showIcon
						locale={ru}
						dateFormat='dd-MM-yyyy'
						selected={this.state.startDate}
						onChange={startDate => this.setState({ startDate: startDate })}
					/>
					<h5 style={{ marginTop: 5 }}>Дата окончания отчетного периода: </h5>
				</div>
				<div>
					<DatePicker
						showIcon
						locale={ru}
						dateFormat='dd-MM-yyyy'
						selected={this.state.endDate}
						onChange={endDate => this.setState({ endDate: endDate })}
					/>
				</div>
				<div>
					<form style={{ marginBottom: -35, marginTop: 5 }} id='external-form'>
						<input type='submit' onClick={this.getEmployeeSummaryByPeriod} />
					</form>
				</div>
				<div className='d-grid gap-2 d-md-flex justify-content-md-end'>
					<button
						onClick={() => this.props.history.push("/add-rest/add")}
						style={{ marginBottom: "5px" }}
						className='btn btn-primary'
					>
						Добавить табель учета времени
					</button>
				</div>
				<BootStrapTable
					bootstrap4
					keyField='id'
					data={this.state.employees}
					columns={this.columns}
					filter={filterFactory()}
					striped
					hover
					condensed
				/>
			</div>
		)
	}
}
export default RestSummaryComponent
