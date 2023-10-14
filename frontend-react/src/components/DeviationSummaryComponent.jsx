import { ru } from "date-fns/locale"
import React, { Component } from "react"
import BootStrapTable from "react-bootstrap-table-next"
import filterFactory, {
	Comparator,
	numberFilter,
	textFilter,
} from "react-bootstrap-table2-filter"
import DatePicker from "react-datepicker"
import DeviationService from "../services/DeviationService"

class DeviationSummaryComponent extends Component {
	constructor(props) {
		super(props)

		var date = new Date()
		this.state = {
			deviations: [],
			employeeName: "",
			deviationCount: "",
			lateCount: "",
			earlyLeavingCount: "",
			absenceCount: "",
			skipCount: "",
			excessDistractionTimeCount: "",
			excessRestTimeCount: "",
			privileges: "",
			yearMonth: new Date(date.getFullYear(), date.getMonth(), 1),
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
			yearMonth: this.state.yearMonth.toLocaleDateString("ru-RU"),
		}

		DeviationService.getAll(filterDto).then(res => {
			this.setState({ deviations: res.data })
		})
	}

	getDeviationSummaryByPeriod = e => {
		e.preventDefault()

		if (this.state.yearMonth === null) {
			alert("Дата отчетного периода не может пустой")
		} else {
			let filterDto = {
				yearMonth: this.state.yearMonth.toLocaleDateString("ru-RU"),
			}

			DeviationService.getAll(filterDto)
				.then(res => {
					this.setState({ employees: res.data })
					this.componentDidMount()
				})
				.catch(err => {
					alert(err.response.data)
				})
		}
	}

	columns = [
		{
			dataField: "employeeName",
			text: "Имя",
			sort: true,
			filter: textFilter({
				placeholder: "Фильтр...",
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "deviationCount",
			text: "Суммарное количество отклонений",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "lateCount",
			text: "Суммарное количество опозданий",
			sort: true,
			filter: textFilter({
				placeholder: "Фильтр...",
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "earlyLeavingCount",
			text: "Суммарное количество ранних уходов",
			sort: true,
			filter: textFilter({
				placeholder: "Фильтр...",
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "absenceCount",
			text: "Суммарное количество отсутствий",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "skipCount",
			text: "Суммарное количество прогулов",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "excessDistractionTimeCount",
			text: "Суммарное количество превышений времени отвлечений",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "excessRestTimeCount",
			text: "Суммарное количество превышений времени перерывов",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "privileges",
			text: "Список привилегий",
			sort: true,
			filter: numberFilter({
				placeholder: "Фильтр...",
				defaultValue: { comparator: Comparator.GT },
			}),
			headerFormatter: this.filterFormatter,
		},
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

	renderMonthContent(month, shortMonth, longMonth) {
		const tooltipText = `Tooltip for month: ${longMonth}`
		return <span title={tooltipText}>{shortMonth}</span>
	}

	render() {
		return (
			<div>
				<h3 className='text-center'>
					Сводная статистика по отклонениям от нормы рабочего времени
					сотрудников
				</h3>
				<div>
					<h5> Отчетный период: </h5>
					<DatePicker
						showIcon
						locale={ru}
						renderMonthContent={this.renderMonthContent}
						showMonthYearPicker
						dateFormat='MM-yyyy'
						selected={this.state.yearMonth}
						onChange={yearMonth => this.setState({ yearMonth: yearMonth })}
					/>
				</div>
				<div></div>
				<div>
					<form style={{ marginBottom: 10, marginTop: 5 }} id='external-form'>
						<input type='submit' onClick={this.getDeviationSummaryByPeriod} />
					</form>
				</div>

				<BootStrapTable
					bootstrap4
					keyField='id'
					data={this.state.deviations}
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
export default DeviationSummaryComponent
