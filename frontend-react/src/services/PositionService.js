import axios from "axios"

const SUMMARY_BASE_URL = "http://localhost:8080/api/v1/summary"
const BASE_URL = "http://localhost:8080/api/v1/position"

class PositionService {
	getAll() {
		return axios.get(BASE_URL)
	}

	getSummaryByPeriod(filterDto) {
		return axios.put(SUMMARY_BASE_URL + "/positionByPeriod", filterDto)
	}

	getById(id) {
		return axios.get(BASE_URL + "/" + id)
	}

	create(position) {
		return axios.post(BASE_URL, position)
	}

	update(position) {
		return axios.put(BASE_URL, position)
	}

	delete(id) {
		return axios.delete(BASE_URL + "/" + id)
	}
}

// eslint-disable-next-line
export default new PositionService()
