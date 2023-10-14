import axios from "axios"

const BASE_URL = "http://localhost:8080/api/v1/deviation"

class PositionService {
	getAll(filterDto) {
		return axios.put(BASE_URL, filterDto)
	}
}

// eslint-disable-next-line
export default new PositionService()
