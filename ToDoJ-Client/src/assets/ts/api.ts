import axios from 'axios'
import type { Task } from '@/assets/ts/types'

const apiUrl = import.meta.env.VITE_API_URL
const taskUrl = import.meta.env.VITE_TASK_URL

export const api = axios.create({
    baseURL: apiUrl
})

export const findAll = async () => {
    try {
        return await api.get(taskUrl)
    } catch (err) {
        if (err instanceof Error) {
            console.error(err)
        }
    }
}

export const findById = async (id:string) => {
    try {
        return await api.get(`${taskUrl}/${id}`)
    } catch (err) {
       if (err instanceof Error) {
           console.error(err)
       }
    }
}

export const insertTask = async (task:Task) => {
    try {
        return await api.post(taskUrl, task)
    } catch (err) {
        if (err instanceof Error) {
            console.error(err)
        }
    }
}

export const deleteTask = async (id:string) => {
    try {
        return await api.delete(`${taskUrl}/${id}`)
    } catch (err) {
        if (err instanceof Error ) {
            console.error(err)
        }
    }
}