import { ru } from "date-fns/locale"
import React, { Component } from "react"
import BootStrapTable from "react-bootstrap-table-next"
import filterFactory, {
	Comparator,
	numberFilter,
	textFilter,
} from "react-bootstrap-table2-filter"
import DatePicker from "react-datepicker"
import DepartmentService from "../services/DepartmentService"

class DepartmentSummaryComponent extends Component {
	constructor(props) {
		super(props)

		var date = new Date()
		this.state = {
			departments: [],
			id: "",
			departmentName: "",
			workTime: "",
			productiveTime: "",
			distractionTime: "",
			restTime: "",
			overTime: "",
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

		DepartmentService.getDepartmentSummaryByPeriod(filterDto).then(res => {
			this.setState({ departments: res.data }).catch(err => {
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
		})
	}

	getDepartmentSummaryByPeriod = e => {
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
			DepartmentService.getDepartmentSummaryByPeriod(filterDto)
				.then(res => {
					this.setState({ departments: res.data })
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
			dataField: "departmentName",
			text: "Отдел",
			sort: true,
			filter: textFilter({
				placeholder: "Фильтр...",
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "workTime",
			text: "Суммарное отработанное время",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "productiveTime",
			text: "Суммарное продуктивное время",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "distractionTime",
			text: "Суммарное время отвлечений",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "restTime",
			text: "Суммарное время перерывов",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "overTime",
			text: "Переработки",
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
					onClick={() => (window.location.href = `/add-department/${row.id}`)}
					className='btn btn-success btn-sm'
					style={{ fontSize: 13 }}
				>
					Редактировать
				</button>

				<button
					style={{ marginTop: 5, fontSize: 13 }}
					onClick={() => {
						DepartmentService.deleteDepartment(row.id)
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
				<h2 className='text-center'>Сводная статистика по отделам</h2>
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
						<input type='submit' onClick={this.getDepartmentSummaryByPeriod} />
					</form>
				</div>
				<div className='d-grid gap-2 d-md-flex justify-content-md-end'>
					<button
						onClick={() => this.props.history.push("/add-department/add")}
						style={{ marginBottom: "5px" }}
						className='btn btn-primary'
					>
						Добавить отдел
					</button>
				</div>
				<BootStrapTable
					bootstrap4
					keyField='id'
					data={this.state.departments}
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

export default DepartmentSummaryComponent
