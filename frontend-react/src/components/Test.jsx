import React, { Component } from "react"
import BootStrapTable from "react-bootstrap-table-next"
import filterFactory, {
	Comparator,
	numberFilter,
	textFilter,
} from "react-bootstrap-table2-filter"
import DepartmentService from "../services/DepartmentService"

class Test extends Component {
	constructor(props) {
		super(props)

		function buttonFormatter(row) {
			return (
				<div>
					<button
						onClick={() => this.editDepartment(row.id)}
						className='btn btn-success btn-sm'
					>
						Редактировать
					</button>
					<button
						style={{ marginTop: "10px" }}
						onClick={() => this.deleteDepartment(row.id)}
						className='btn btn-danger btn-sm'
					>
						Удалить
					</button>
				</div>
			)
		}

		function priceFormatter(column, colIndex, { sortElement, filterElement }) {
			return (
				<div style={{ display: "flex", flexDirection: "column" }}>
					{filterElement}
					{column.text}
					{sortElement}
				</div>
			)
		}

		this.columns = [
			{
				dataField: "departmentName",
				text: "Наименование",
				sort: true,
				filter: textFilter({
					placeholder: "Фильтр...",
				}),
				headerFormatter: priceFormatter,
			},
			{
				dataField: "workTime",
				text: "Суммарное отработанное время",
				sort: true,
				filter: numberFilter({
					placeholder: "Фильтр...",
					defaultValue: { comparator: Comparator.GT },
				}),
				headerFormatter: priceFormatter,
			},
			{ dataField: "productiveTime", text: "Суммарное продуктивное время" },
			{ dataField: "distractionTime", text: "Суммарное время отвлечений" },
			{ dataField: "restTime", text: "Суммарное время перерывов" },
			{ dataField: "overTime", text: "Переработки" },
			{ text: "Действия", formatter: buttonFormatter },
		]
		this.state = {
			departments: [],
			id: "",
			departmentName: "",
			workTime: "",
			productiveTime: "",
			distractionTime: "",
			restTime: "",
			overTime: "",
		}
	}

	componentDidMount() {
		DepartmentService.getDepartmentSummary().then(res => {
			this.setState({ departments: res.data })
		})
	}

	render() {
		return (
			<div>
				<h2 className='text-center'>Сводка по отделам</h2>
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

export default Test
