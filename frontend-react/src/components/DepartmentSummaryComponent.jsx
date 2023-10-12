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

		// this.buttonFormatter = this.buttonFormatter.bind(this)
		// this.deleteDepartment = this.deleteDepartment.bind(this)
	}

	componentDidMount() {
		DepartmentService.getDepartmentSummary().then(res => {
			this.setState({ departments: res.data })
		})
	}

	columns = [
		{
			dataField: "departmentName",
			text: "Наименование",
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
		{
			text: "Переработки",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{ text: "Редактировать", formatter: this.editFormatter },
		{ text: "Удалить", formatter: this.deleteFormatter },
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

	editFormatter(cell, row, rowIndex) {
		return (
			<div>
				<button
					onClick={() => this.props.history.push(`/add-department/${row.id}`)}
					className='btn btn-success'
				>
					Редактировать
				</button>
			</div>
		)
	}

	deleteFormatter(cell, row, rowIndex) {
		return (
			<div>
				<button
					onClick={() => {
						DepartmentService.deleteDepartment(row.id)
							.catch(err => {
								alert(err.response.data)
							})
							.then(res => {})
					}}
					className='btn btn-danger'
				>
					Удалить
				</button>
			</div>
		)
	}

	deleteDepartment(id) {
		DepartmentService.deleteDepartment(id)
			.catch(err => {
				alert(err.response.data)
			})
			.then(res => {})

		console.log(id)
	}

	getDepartmentSummaryByPeriod = e => {
		e.preventDefault()

		if (this.state.startDate === null) {
			alert("Дата начала отчетного периода не может пустой")
		} else if (this.state.endDate === null) {
			alert("Дата окончания отчетного периода не может пустой")
		} else {
			let filterDto = {
				startDate: this.state.startDate.toISOString(),
				endDate: this.state.endDate.toISOString(),
			}

			DepartmentService.getDepartmentSummaryByPeriod(filterDto)
				.then(res => {
					this.setState({ departments: res.data })
				})
				.catch(err => {
					alert(err.response.data)
					this.componentDidMount()
				})
		}
	}

	render() {
		return (
			<div>
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
					<form style={{ marginBottom: 10, marginTop: 5 }} id='external-form'>
						<input type='submit' onClick={this.getDepartmentSummaryByPeriod} />
					</form>
				</div>
				<BootStrapTable
					bootstrap4
					keyField='name'
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
