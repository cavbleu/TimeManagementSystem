import axios from "axios"

const SUMMARY_BASE_URL = "http://localhost:8080/api/v1/summary"
const POSITION_BASE_URL = "http://localhost:8080/api/v1/position"

class PositionService {

	getSummaryByPeriod(filterDto) {
		return axios.put(SUMMARY_BASE_URL + "/positionByPeriod", filterDto)
	}

	getById(id) {
		return axios.get(POSITION_BASE_URL + "/" + id)
	}

	create(position) {
		return axios.post(POSITION_BASE_URL, position)
	}

	update(position) {
		return axios.put(POSITION_BASE_URL, position)
	}

	delete(id) {
		return axios.delete(POSITION_BASE_URL + "/" + id)
	}
}

// eslint-disable-next-line
export default new PositionService()
