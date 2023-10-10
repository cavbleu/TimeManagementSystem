import React, {Component} from 'react';
import VehicleService from '../services/VehicleService';

class ListVehicleComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            vehicles: [],
            brand: '',
            model: '',
            category: '',
            gosNumber: '',
            productionYear: '',
            vehicleType: ''
        }
        this.changeBrandHandler = this.changeBrandHandler.bind(this);
        this.addVehicle = this.addVehicle.bind(this);
        this.editVehicle = this.editVehicle.bind(this);
        this.deleteVehicle = this.deleteVehicle.bind(this);
    }

    componentDidMount() {
        VehicleService.getVehicles().then((res) => {
            this.setState({vehicles: res.data});
        });
    }

    addVehicle() {
        this.props.history.push('/add-vehicle/_add');
    }

    editVehicle(id) {
        this.props.history.push(`/add-vehicle/${id}`)
    }

    deleteVehicle(id) {
        VehicleService.deleteVehicle(id).then(res => {
            this.setState({
                vehicles: this.state.vehicles.filter(vehicle => vehicle.id !== id)
            });
        });
    }

    changeBrandHandler = (event) => {
        this.setState({brand: event.target.value});
    }
    changeModelHandler = (event) => {
        this.setState({model: event.target.value});
    }
    changeCategoryHandler = (event) => {
        this.setState({category: event.target.value});
    }
    changeGosNumberHandler = (event) => {
        this.setState({gosNumber: event.target.value});
    }
    changeProductionYearHandler = (event) => {
        this.setState({productionYear: event.target.value});
    }
    changeVehicleType = (event) => {
        this.setState({vehicleType: event.target.value});
    }

    handleKeyDownBrand = (event) => {
        if (event.key === 'Enter') {
            if (this.state.brand !== "") {
                VehicleService.getBrandFilter(this.state.brand).then((res) => {
                    this.setState({vehicles: res.data});
                });
            } else {
                VehicleService.getVehicles().then((res) => {
                    this.setState({vehicles: res.data});
                });
            }
        }
    }
    handleKeyDownModel = (event) => {
        if (event.key === 'Enter') {
            if (this.state.model !== "") {
                VehicleService.getModelFilter(this.state.model).then((res) => {
                    this.setState({vehicles: res.data});
                });
            } else {
                VehicleService.getVehicles().then((res) => {
                    this.setState({vehicles: res.data});
                });
            }
        }
    }
    handleKeyDownCategory = (event) => {
        if (event.key === 'Enter') {
            if (this.state.category !== "") {
                VehicleService.getCategoryFilter(this.state.category).then((res) => {
                    this.setState({vehicles: res.data});
                });
            } else {
                VehicleService.getVehicles().then((res) => {
                    this.setState({vehicles: res.data});
                });
            }
        }
    }
    handleKeyDownGosNumber = (event) => {
        if (event.key === 'Enter') {
            if (this.state.gosNumber !== "") {
                VehicleService.getGosNumberFilter(this.state.gosNumber).then((res) => {
                    this.setState({vehicles: res.data});
                });
            } else {
                VehicleService.getVehicles().then((res) => {
                    this.setState({vehicles: res.data});
                });
            }
        }
    }
    handleKeyDownProductionYear = (event) => {
        if (event.key === 'Enter') {
            if (this.state.productionYear !== "") {
                VehicleService.getProductionYearFilter(this.state.productionYear).then((res) => {
                    this.setState({vehicles: res.data});
                });
            } else {
                VehicleService.getVehicles().then((res) => {
                    this.setState({vehicles: res.data});
                });
            }
        }
    }
    handleKeyVehicleType = (event) => {
        if (event.key === 'Enter') {
            if (this.state.vehicleType !== "") {
                VehicleService.getVehicleTypeFilter(this.state.vehicleType).then((res) => {
                    this.setState({vehicles: res.data});
                });
            } else {
                VehicleService.getVehicles().then((res) => {
                    this.setState({vehicles: res.data});
                });
            }
        }
    }


    render() {
        return (
            <div>
                <h2 className='text-center'>Транспортные средства</h2>
                <div className='row'>
                    <table className='table table-stripped table-bordered'>
                        <thead>

                        <tr>
                            <th className='text-center'>
                                <input name='brand' className='form-control'
                                       value={this.state.brand}
                                       onChange={this.changeBrandHandler}
                                       onKeyDown={this.handleKeyDownBrand}
                                /></th>
                            <th className='text-center'>
                                <input name='model' className='form-control'
                                       value={this.state.model}
                                       onChange={this.changeModelHandler}
                                       onKeyDown={this.handleKeyDownModel}
                                />
                            </th>
                            <th className='text-center'>
                                <input name='category' className='form-control'
                                       value={this.state.category}
                                       onChange={this.changeCategoryHandler}
                                       onKeyDown={this.handleKeyDownCategory}
                                />
                            </th>
                            <th className='text-center'>
                                <input name='gosNumber' className='form-control'
                                       value={this.state.gosNumber}
                                       onChange={this.changeGosNumberHandler}
                                       onKeyDown={this.handleKeyDownGosNumber}
                                />
                            </th>
                            <th className='text-center'>
                                <input name='productionYear' className='form-control'
                                       value={this.state.productionYear}
                                       onChange={this.changeProductionYearHandler}
                                       onKeyDown={this.handleKeyDownProductionYear}
                                />
                            </th>
                            <th className='text-center'>
                                <input name='vehicleType' className='form-control'
                                       value={this.state.vehicleType}
                                       onChange={this.changeVehicleType}
                                       onKeyDown={this.handleKeyVehicleType}
                                />
                            </th>

                        </tr>
                        <tr>
                            <th className='text-center'>Марка</th>
                            <th className='text-center'>Модель</th>
                            <th className='text-center'>Категория</th>
                            <th className='text-center'>Гос. номер</th>
                            <th className='text-center'>Год выпуска</th>
                            <th className='text-center'>Тип ТС</th>
                            <th className='text-center'>
                                <button style={{marginBottom: '5px'}} className='btn btn-primary'
                                        onClick={this.addVehicle}>Добавить
                                    ТС
                                </button>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.vehicles.map(
                                vehicle =>
                                    <tr key={vehicle.id}>
                                        <td>{vehicle.brand}</td>
                                        <td>{vehicle.model}</td>
                                        <td>{vehicle.category}</td>
                                        <td>{vehicle.gosNumber}</td>
                                        <td>{vehicle.productionYear}</td>
                                        <td>{vehicle.vehicleType}</td>
                                        <td style={{width: '250px'}}>
                                            <button onClick={() => this.editVehicle(vehicle.id)}
                                                    className='btn btn-success btn-sm'>Редактировать
                                            </button>
                                            <button style={{marginLeft: '10px'}}
                                                    onClick={() => this.deleteVehicle(vehicle.id)}
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

export default ListVehicleComponent;