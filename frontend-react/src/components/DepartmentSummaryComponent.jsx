import React, {Component} from 'react';
import DepartmentService from '../services/DepartmentService';

class DepartmentSummaryComponent extends Component {
    constructor(props) {
        super(props)

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
        this.changeBrandHandler = this.changeBrandHandler.bind(this);
        this.addDepartment = this.addDepartment.bind(this);
        this.editDepartment = this.editDepartment.bind(this);
        this.deleteDepartment = this.deleteDepartment.bind(this);
    }

    componentDidMount() {
        DepartmentService.getDepartmentSummary().then((res) => {
            this.setState({departments: res.data});
        });
    }

    addDepartment() {
        this.props.history.push('/add-vehicle/_add');
    }

    editDepartment(id) {
        this.props.history.push(`/add-vehicle/${id}`)
    }

    deleteDepartment(id) {
        DepartmentService.deleteVehicle(id).then(res => {
            this.setState({
                vehicles: this.state.vehicles.filter(vehicle => vehicle.id !== id)
            });
        });
    }

    changeBrandHandler = (event) => {
        this.setState({brand: event.target.value});
    }

    handleKeyDownBrand = (event) => {
        if (event.key === 'Enter') {
            if (this.state.brand !== "") {
                DepartmentService.getBrandFilter(this.state.brand).then((res) => {
                    this.setState({vehicles: res.data});
                });
            } else {
                DepartmentService.getVehicles().then((res) => {
                    this.setState({vehicles: res.data});
                });
            }
        }
    }
    handleKeyDownModel = (event) => {
        if (event.key === 'Enter') {
            if (this.state.model !== "") {
                DepartmentService.getModelFilter(this.state.model).then((res) => {
                    this.setState({vehicles: res.data});
                });
            } else {
                DepartmentService.getVehicles().then((res) => {
                    this.setState({vehicles: res.data});
                });
            }
        }
    }
    handleKeyDownCategory = (event) => {
        if (event.key === 'Enter') {
            if (this.state.category !== "") {
                DepartmentService.getCategoryFilter(this.state.category).then((res) => {
                    this.setState({vehicles: res.data});
                });
            } else {
                DepartmentService.getVehicles().then((res) => {
                    this.setState({vehicles: res.data});
                });
            }
        }
    }
    handleKeyDownGosNumber = (event) => {
        if (event.key === 'Enter') {
            if (this.state.gosNumber !== "") {
                DepartmentService.getGosNumberFilter(this.state.gosNumber).then((res) => {
                    this.setState({vehicles: res.data});
                });
            } else {
                DepartmentService.getVehicles().then((res) => {
                    this.setState({vehicles: res.data});
                });
            }
        }
    }
    handleKeyDownProductionYear = (event) => {
        if (event.key === 'Enter') {
            if (this.state.productionYear !== "") {
                DepartmentService.getProductionYearFilter(this.state.productionYear).then((res) => {
                    this.setState({vehicles: res.data});
                });
            } else {
                DepartmentService.getVehicles().then((res) => {
                    this.setState({vehicles: res.data});
                });
            }
        }
    }
    handleKeyVehicleType = (event) => {
        if (event.key === 'Enter') {
            if (this.state.vehicleType !== "") {
                DepartmentService.getVehicleTypeFilter(this.state.vehicleType).then((res) => {
                    this.setState({vehicles: res.data});
                });
            } else {
                DepartmentService.getVehicles().then((res) => {
                    this.setState({vehicles: res.data});
                });
            }
        }
    }


    render() {
        return (
            <div>
                <h2 className='text-center'>Система мониторинга рабочего времени сотрудников</h2>
                <div className='row'>
                    <table className='table table-striped table-bordered'>
                        <thead>

                        <tr>
                            <th className='text-center'>
                                <input name='brand' className='form-control'
                                       value={this.state.departmentName}
                                       onChange={this.changeBrandHandler}
                                       onKeyDown={this.handleKeyDownBrand}
                                /></th>
                            <th>
                                <input name='model' className='form-control'
                                       value={this.state.workTime}
                                       onChange={this.changeModelHandler}
                                       onKeyDown={this.handleKeyDownModel}
                                />
                            </th>
                            <th>
                                <input name='category' className='form-control'
                                       value={this.state.productiveTime}
                                       onChange={this.changeCategoryHandler}
                                       onKeyDown={this.handleKeyDownCategory}
                                />
                            </th>
                            <th>
                                <input name='gosNumber' className='form-control'
                                       value={this.state.distractionTime}
                                       onChange={this.changeGosNumberHandler}
                                       onKeyDown={this.handleKeyDownGosNumber}
                                />
                            </th>
                            <th>
                                <input name='productionYear' className='form-control'
                                       value={this.state.restTime}
                                       onChange={this.changeProductionYearHandler}
                                       onKeyDown={this.handleKeyDownProductionYear}
                                />
                            </th>
                            <th>
                                <input name='vehicleType' className='form-control'
                                       value={this.state.overTime}
                                       onChange={this.changeVehicleType}
                                       onKeyDown={this.handleKeyVehicleType}
                                />
                            </th>
                            <th>Действия</th>

                        </tr>
                        <tr>
                            <th>Наименование отдела</th>
                            <th>Суммарное отработанное время</th>
                            <th>Суммарное продуктивное время</th>
                            <th>Суммарное время отвлечений</th>
                            <th>Суммарное время перерывов</th>
                            <th>Переработки</th>
                            <th className='text-center'>
                                <button className='btn btn-primary'
                                        onClick={this.addDepartment}>Добавить
                                    отдел
                                </button>
                            </th>
                        </tr>
                        </thead>
                        <tbody style={{textAlign: "center"}}>
                        {
                            this.state.departments.map(
                                department =>
                                    <tr key={department.id}>
                                        <td>{department.departmentName}</td>
                                        <td>{department.workTime}</td>
                                        <td>{department.productiveTime}</td>
                                        <td>{department.distractionTime}</td>
                                        <td>{department.restTime}</td>
                                        <td>{department.overTime}</td>
                                        <td style={{width: '250px'}}>
                                            <button onClick={() => this.editDepartment(department.id)}
                                                    className='btn btn-success btn-sm'>Редактировать
                                            </button>
                                            <button style={{marginLeft: '10px'}}
                                                    onClick={() => this.deleteDepartment(department.id)}
                                                    className='btn btn-danger btn-sm'>Удалить
                                            </button>
                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default DepartmentSummaryComponent;