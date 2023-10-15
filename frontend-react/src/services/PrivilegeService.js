import axios from "axios"

const BASE_URL = "http://localhost:8080/api/v1/privilege"

class PrivilegeService {
	getById(id) {
		return axios.get(BASE_URL + "/" + id)
	}

	getAll() {
		return axios.get(BASE_URL)
	}

	update(rest) {
		return axios.put(BASE_URL, rest)
	}
}

// eslint-disable-next-line
export default new PrivilegeService()
