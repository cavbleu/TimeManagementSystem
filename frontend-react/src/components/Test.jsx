import React, {Component} from 'react';
import DepartmentService from "../services/DepartmentService";
import BootStrapTable from "react-bootstrap-table-next"
import filterFactory, {numberFilter, textFilter} from "react-bootstrap-table2-filter";

class Test extends Component {

    constructor(props) {
        super(props)

        function buttonFormatter(row) {
            return (
                <div>
                    <button onClick={() => this.editDepartment(row.id)}
                            className='btn btn-success btn-sm'>Редактировать
                    </button>
                    <button style={{marginTop: '10px'}}
                            onClick={() => this.deleteDepartment(row.id)}
                            className='btn btn-danger btn-sm'>Удалить
                    </button>
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
                })
            },
            {dataField: "workTime",
                text: "Суммарное отработанное время",
                filter: numberFilter()
                // (
                //     {
                //     placeholder: "Фильтр...",
                // }
                // )
            },
            {dataField: "productiveTime", text: "Суммарное продуктивное время"},
            {dataField: "distractionTime", text: "Суммарное время отвлечений"},
            {dataField: "restTime", text: "Суммарное время перерывов"},
            {dataField: "overTime", text: "Переработки"},
            {text: "Действия", formatter: buttonFormatter}
        ]
        this.state = {
            departments: [],
            id: '',
            departmentName: '',
            workTime: '',
            productiveTime: '',
            distractionTime: '',
            restTime: '',
            overTime: ''
        }
    }


    componentDidMount() {
        DepartmentService.getDepartmentSummary().then((res) => {
            this.setState({departments: res.data});
        });
    }

    render() {
        return (
            <div>
                <BootStrapTable
                    keyField='name'
                    data={this.state.departments}
                    columns={this.columns}
                    filter={filterFactory()}
                    striped
                    hover
                    condensed
                />
            </div>
        );
    }
}

export default Test;