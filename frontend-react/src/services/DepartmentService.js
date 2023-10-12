import axios from "axios"

const SUMMARY_BASE_URL = "http://localhost:8080/api/v1/summary"
const DEPARTMENT_BASE_URL = "http://localhost:8080/api/v1/department"

class DepartmentService {
	getAllDepartments() {
		return axios.get(DEPARTMENT_BASE_URL)
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

	getAll() {
		return axios.get(DEPARTMENT_BASE_URL)
	}
}

// eslint-disable-next-line
export default new DepartmentService()
