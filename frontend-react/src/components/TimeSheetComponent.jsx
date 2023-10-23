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
import TimeSheetService from "../services/TimeSheetService"

class Helper {
	constructor(name, timeSheet, position) {
		this.name = name
		this.timeSheet = timeSheet
		this.position = position
	}
}

class TimeSheetComponent extends Component {
	constructor(props) {
		super(props)

		var date = new Date()
		this.state = {
			employees: [],
			id: "",
			name: "",
			timeSheet: {
				id: "",
				date: "",
				absenceReason: "",
				startWork: "",
				endWork: "",
				workTime: "",
			},
			position: {
				id: "",
				name: "",
				department: {
					id: "",
					name: "",
				},
			},
			startDate: new Date(date.getFullYear(), date.getMonth(), 1),
			endDate: new Date(date.getFullYear(), date.getMonth() + 1, 0),
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
		EmployeeService.getAllByPeriod(filterDto)
			.then(res => {
				let ar = []

				res.data.map(data => {
					data.timeSheets.map(t => {
						ar.push(new Helper(data.name, t, data.position, data.id))
					})
				})

				ar.sort(
					(a, b) =>
						moment(b.timeSheet.date, "DD.MM.YY") -
						moment(a.timeSheet.date, "DD.MM.YY")
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
			dataField: "timeSheet.date",
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
			dataField: "timeSheet.startWork",
			text: "Начало рабочего дня",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "timeSheet.endWork",
			text: "Окончание рабочего дня",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "timeSheet.workTime",
			text: "Отработанное время, мин",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "timeSheet.absenceReason",
			text: "Причина отсутствия",
			sort: true,
			filter: textFilter({
				placeholder: "Фильтр...",
			}),
			headerFormatter: this.filterFormatter,
		},
		{ text: "Действия", formatter: this.buttonFormatter },
	]

	filterFormatter(column, colIndex, { sortElement, filterElement }) {
		return (
			<div style={{ display: "flex", flexDirection: "column", fontSize: 13 }}>
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
					onClick={() =>
						(window.location.href = `/add-timeSheet/${row.timeSheet.id}`)
					}
					className='btn btn-success btn-sm'
					style={{ fontSize: 13 }}
				>
					Редактировать
				</button>

				<button
					style={{ marginTop: 5, fontSize: 13 }}
					onClick={() => {
						TimeSheetService.delete(row.timeSheet.id)
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
			<div style={{ marginTop: 20, fontSize: 13 }}>
				<h2 className='text-center'>Табели рабочего времени сотрудников</h2>
				<div>
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
						onClick={() => this.props.history.push("/add-timeSheet/add")}
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
export default TimeSheetComponent
