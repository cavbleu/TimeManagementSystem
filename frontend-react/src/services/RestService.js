import axios from "axios"

const BASE_URL = "http://localhost:8080/api/v1/rest"

class RestService {
	getById(id) {
		return axios.get(BASE_URL + "/" + id)
	}

	create(rest) {
		return axios.post(BASE_URL, rest)
	}

	update(rest) {
		return axios.put(BASE_URL, rest)
	}

	delete(id) {
		return axios.delete(BASE_URL + "/" + id)
	}
}

// eslint-disable-next-line
export default new RestService()
