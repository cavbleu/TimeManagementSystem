import axios from "axios"

const SUMMARY_BASE_URL = "http://localhost:8080/api/v1/summary"
const BASE_URL = "http://localhost:8080/api/v1/department"

class DepartmentService {
	getAllDepartments() {
		return axios.get(BASE_URL)
	}

	getDepartmentSummaryByPeriod(filterDto) {
		return axios.put(SUMMARY_BASE_URL + "/departmentByPeriod", filterDto)
	}

	getDepartmentById(id) {
		return axios.get(BASE_URL + "/" + id)
	}

	createDepartment(department) {
		return axios.post(BASE_URL, department)
	}

	updateDepartment(department) {
		return axios.put(BASE_URL, department)
	}

	deleteDepartment(id) {
		return axios.delete(BASE_URL + "/" + id)
	}

	getAll() {
		return axios.get(BASE_URL)
	}
}

// eslint-disable-next-line
export default new DepartmentService()
