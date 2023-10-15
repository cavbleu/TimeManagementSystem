import axios from "axios"

const SUMMARY_BASE_URL = "http://localhost:8080/api/v1/summary"
const BASE_URL = "http://localhost:8080/api/v1/employee"

class EmployeeService {
	getAll() {
		return axios.get(BASE_URL)
	}

	getAllByPeriod(filterDto) {
		return axios.put(BASE_URL + "/byPeriod", filterDto)
	}

	getSummaryByPeriod(filterDto) {
		return axios.put(SUMMARY_BASE_URL + "/employeeByPeriod", filterDto)
	}

	getById(id) {
		return axios.get(BASE_URL + "/" + id)
	}

	create(employee) {
		return axios.post(BASE_URL, employee)
	}

	update(employee) {
		return axios.put(BASE_URL, employee)
	}

	delete(id) {
		return axios.delete(BASE_URL + "/" + id)
	}
}

// eslint-disable-next-line
export default new EmployeeService()
