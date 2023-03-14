<template>
    <div class="the-task-form">
        <form @submit.prevent="checkFields()">
            <div class="form-header">
                <input v-model="taskTitle" placeholder="insert a task title">
                <button @click="$emit('updateTaskList')" class="add-button">Adicionar</button>
            </div>
            <textarea v-model="taskInfo" class="task-content" cols="54" rows="6"
                placeholder="Insert information about the task.">
                </textarea>
            <div class="errorFieldsEmpty" v-if="isVisible">
                <p>One or More fields are empty (title or task content)</p>
            </div>
        </form>
    </div>
</template>

<script setup lang="ts">
import { insertTask } from "@/assets/ts/api"
import { ref } from "vue"
import type { Task } from "@/assets/ts/types"

const emit = defineEmits({
    updateTaskList: null
})

let isVisible = ref(false)
let taskTitle = ref("")
let taskInfo = ref("")

const checkFields = () => {
    
    const task:Task = {
        title: taskTitle.value,
        taskInfo: taskInfo.value,
    }

    if (task.title == "") {
        isVisible.value = !isVisible.value
        return
    }

    if (task.taskInfo == "") {
        isVisible.value = !isVisible.value
        return
    }
        
    isVisible.value = false
    addTask(task)
}

const addTask = async (task: Task) => {
    
    await insertTask(task)

    emit("updateTaskList")

    taskInfo.value = ""
    taskTitle.value = ""
}

</script>

<style scoped lang="scss">
@import url("@/assets/styles/fonts.css");

.the-task-form {
    max-width: 480px;
    width: 480px;
    display: flex;
    flex-wrap: wrap;
    margin: 10px auto 0;
    padding: 0 1.8px;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    align-content: space-between;
    transition: all .5s ease-in-out;

    .form-header {
        display: grid;

        input {
            font-style: oblique;
            grid-column-start: 1;
            grid-column-end: 4;
            border: 1px solid #000;
            border-radius: 10px;
            margin: 0 15px 15px 0;
            padding: 10px;
            transition: all .2s ease-in-out;

            &:hover {
                transform: translate(10px, 0);
            }
        }

        .add-button {
            grid-column-start: 4;
            grid-column-end: 5;
            border: 1px solid #000;
            border-radius: 10px;
            margin: 0 0 15px 15px;
            padding: 10px;
            transition: all .2s ease-in-out;

            &:hover {
                background-color: #80ff0082;
                transform: scale(1.1);
            }
        }
    }

    .task-content {
        border: 1px solid #000;
        border-radius: 10px;
        max-width: 480px;
        margin: 15px auto;
        padding: 8.14px;
        display: block;
        transition: all .2s ease-in-out;

        &:hover {
            transform: translate(10px, 0);
        }
    }

    h1 {
        font-size: x-large;
    }

    .errorFieldsEmpty {
        border: 7.5px dotted red;
        border-radius: 10px;
        text-align: center;
        background-color: tomato;
        padding: 10px;
        color: white;
        font-family: "fredoka one", "Courier New", Courier, monospace;
        animation: swinging-left .7s infinite alternate;
        animation: swinging-right .7s infinite alternate;
    }

    @keyframes swinging-right {
        from {
            transform: translateX(-10px);
        } to {
            transform: translateX(10px);
        }
    }
}
</style>