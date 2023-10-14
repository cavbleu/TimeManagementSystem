import axios from "axios"

const BASE_URL = "http://localhost:8080/api/v1/timesheet"

class TimeSheetService {
	getById(id) {
		return axios.get(BASE_URL + "/" + id)
	}

	create(timeSheet) {
		return axios.post(BASE_URL, timeSheet)
	}

	update(timeSheet) {
		return axios.put(BASE_URL, timeSheet)
	}

	delete(id) {
		return axios.delete(BASE_URL + "/" + id)
	}
}

// eslint-disable-next-line
export default new TimeSheetService()
