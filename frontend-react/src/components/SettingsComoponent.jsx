import React, { Component } from "react"
import BootStrapTable from "react-bootstrap-table-next"
import filterFactory, {
	Comparator,
	numberFilter,
	textFilter,
} from "react-bootstrap-table2-filter"
import SettingsService from "../services/SettingsService"

class SettingsComponent extends Component {
	constructor(props) {
		super(props)

		var date = new Date()
		this.state = {
			settings: [],
			id: "",
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
		}
	}

	componentDidMount() {
		SettingsService.getAll()
			.then(res => {
				this.setState({
					settings: res.data,
				})
			})
			.catch(err => {
				alert(err.response.data)
			})
	}

	columns = [
		{
			dataField: "name",
			text: "Наименование профиля",
			sort: true,
			filter: textFilter({
				placeholder: "Фильтр...",
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "currentSettingsProfile",
			text: "Активен",
			sort: true,
			filter: textFilter({
				placeholder: "Фильтр...",
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "defaultWorkTime",
			text: "Норма рабочего времени в сутки",
			sort: true,
			filter: textFilter({
				placeholder: "Фильтр...",
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "defaultStartWork",
			text: "Норма начала рабочего дня",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "maxLateCountByMonth",
			text: "Максимальное количество опозданий в месяц",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "maxEarlyLivingCountByMonth",
			text: "Максимальное количество уходов с работы до истечения нормы рабочего времени",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "maxAbsenceCountByMonth",
			text: "Максимальное количество отсутствий за месяц",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "maxSkipCountByMonth",
			text: "Максимальное количество прогулов за месяц",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "maxDistractionTimeByDay",
			text: "Максимальное суммарное время отвлечений в день",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "maxRestTimeByDay",
			text: "Максимальное суммарное время перерывов в день",
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
					onClick={() => (window.location.href = `/add-settings/${row.id}`)}
					className='btn btn-success'
					style={{ fontSize: 10 }}
				>
					Редактировать
				</button>

				<button
					style={{ marginTop: 5, fontSize: 10 }}
					onClick={() => {
						SettingsService.delete(row.id)
							.catch(err => {
								alert(err.response.data)
							})
							.then(res => {
								window.location.reload()
							})
					}}
					className='btn btn-danger'
				>
					Удалить
				</button>
			</div>
		)
	}

	render() {
		return (
			<div style={{ marginTop: 20, fontSize: 13 }}>
				<h2 className='text-center'>
					Настройки лимитов распорядка дня сотрудников
				</h2>

				<div className='d-grid gap-2 d-md-flex justify-content-md-end'>
					<button
						onClick={() => this.props.history.push("/add-settings/add")}
						style={{ marginBottom: "5px" }}
						className='btn btn-primary'
					>
						Добавить профиль настроек
					</button>
				</div>
				<div>
					<BootStrapTable
						bootstrap4
						keyField='id'
						data={this.state.settings}
						columns={this.columns}
						filter={filterFactory()}
						striped
						hover
						condensed
					/>
				</div>
			</div>
		)
	}
}
export default SettingsComponent
