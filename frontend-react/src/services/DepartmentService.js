import axios from "axios"

const SUMMARY_BASE_URL = "http://localhost:8080/api/v1/summary"
const DEPARTMENT_BASE_URL = "http://localhost:8080/api/v1/department"

class DepartmentService {
	getDepartmentSummary() {
		return axios.get(SUMMARY_BASE_URL + "/department").catch(err => {
			console.log(err)
		})
	}

	getDepartmentSummaryByPeriod(filterDto) {
		return axios.put(SUMMARY_BASE_URL + "/departmentByPeriod", filterDto)
	}

	getDepartmentById(id) {
		return axios.get(DEPARTMENT_BASE_URL + "/" + id)
	}

	createDepartment(department) {
		return axios.post(DEPARTMENT_BASE_URL, department)
	}

	updateDepartment(department) {
		return axios.put(DEPARTMENT_BASE_URL, department)
	}

	deleteDepartment(id) {
		return axios.delete(DEPARTMENT_BASE_URL + "/" + id)
	}

	// createVehicle(vehicle) {
	//     return axios.post(VEHICLE_BASE_URL, vehicle)
	// }
	//
	// getVehicleById(vehicleId) {
	//     return axios.get(VEHICLE_BASE_URL + '/' + vehicleId)
	// }
	//
	// updateVehicle(employee, vehicleId) {
	//     return axios.put(VEHICLE_BASE_URL + '/' + vehicleId, employee);
	// }
	//
	// deleteVehicle(vehicleId) {
	//     return axios.delete(VEHICLE_BASE_URL + '/' + vehicleId);
	// }
	//
	// getBrandFilter(filterPhrase) {
	//     return axios.get(VEHICLE_FILTER_BRAND_URL + '/' + filterPhrase);
	// }
	//
	// getModelFilter(filterPhrase) {
	//     return axios.get(VEHICLE_FILTER_MODEL_URL + '/' + filterPhrase);
	// }
	//
	// getCategoryFilter(filterPhrase) {
	//     return axios.get(VEHICLE_FILTER_CATEGORY_URL + '/' + filterPhrase);
	// }
	//
	// getGosNumberFilter(filterPhrase) {
	//     return axios.get(VEHICLE_FILTER_GOSNUMBER_URL + '/' + filterPhrase);
	// }
	//
	// getProductionYearFilter(filterPhrase) {
	//     return axios.get(VEHICLE_FILTER_YEAR_URL + '/' + filterPhrase);
	// }
	//
	// getVehicleTypeFilter(filterPhrase) {
	//     return axios.get(VEHICLE_FILTER_TYPE_URL + '/' + filterPhrase);
	// }
}

// eslint-disable-next-line
export default new DepartmentService()
