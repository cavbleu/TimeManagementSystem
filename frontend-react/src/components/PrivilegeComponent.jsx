import React, { Component } from "react"
import BootStrapTable from "react-bootstrap-table-next"
import filterFactory, { textFilter } from "react-bootstrap-table2-filter"
import PrivilegeService from "../services/PrivilegeService"

class PrivilegeComponent extends Component {
	constructor(props) {
		super(props)

		this.state = {
			privilege: [],
			id: "",
			name: "",
			increasedAmount: "",
		}
	}

	componentDidMount() {
		PrivilegeService.getAll()
			.then(res => {
				this.setState({
					privilege: res.data,
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

	columns = [
		{
			dataField: "name",
			text: "Привилегия",
			sort: true,
			filter: textFilter({
				placeholder: "Фильтр...",
			}),
			headerFormatter: this.filterFormatter,
		},
		{
			dataField: "increasedAmount",
			text: "Значение",
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
					onClick={() => (window.location.href = `/upd-privilege/${row.id}`)}
					className='btn btn-success btn-sm'
					style={{ fontSize: 15 }}
				>
					Редактировать
				</button>
			</div>
		)
	}

	render() {
		return (
			<div style={{ marginTop: 20 }}>
				<h2 className='text-center'>Настройки привилегий</h2>

				<div style={{ fontSize: 16 }}>
					<BootStrapTable
						bootstrap4
						keyField='id'
						data={this.state.privilege}
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
export default PrivilegeComponent
