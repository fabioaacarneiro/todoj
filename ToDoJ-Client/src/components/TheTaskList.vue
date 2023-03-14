<template>
    <TheTaskForm @updateTaskList="getTasks()" />
    <div class="the-task-list">
        <ul v-for="task in taskInverse" class="task.id">
            <li @click="checkedTask(task)" :class="task.status">
                <div class="task-title">
                    {{ task.title }} 
                    <span v-if="task.statusMessage" class="status-task-message">Concluído</span>
                    <TheDeleteTaskButton :idTask="task.id" @updateTaskList="getTasks()"/>
                </div>
                <div class="task-content">
                    {{ task.taskInfo }}
                </div>
            </li>
        </ul>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from "vue"
import { findAll } from "@/assets/ts/api"

import TheTaskForm from "./TheTaskForm.vue"
import TheDeleteTaskButton from "./TheDeleteTaskButton.vue"

let tasks = ref()

const taskInverse = computed(() => tasks.value?.slice().reverse() ?? [])

const checkedTask = (tasks:any) => {

    if (tasks.status == undefined || tasks.status == "") {
        tasks.status = "task-done"
        tasks.statusMessage = true

        return
    }

    tasks.status = ""
    tasks.statusMessage = false
}

const getTasks = async () => {
    const response = await findAll()
    tasks.value = response?.data
}

onMounted(() => {
    getTasks()
})
</script>

<style lang="scss" scoped>
@import url("@/assets/styles/fonts.css");

.the-task-list {

    display: flex;
    flex-direction: column;
    justify-content: center;
    max-width: 30rem;
    margin: auto;

    li {
        border: 1px solid #000;
        border-radius: .5em;
        list-style: none;
        margin: 15px auto;
        padding: 10px 15px;
        min-height: 100px;
        transition: all .2s ease-in-out;

        &:hover {
            background-color: #41b88388;
            transform: translate(10px, 0);
        }
    }
    .task-done {
        background-color: #41b88344;
    }

    .task-title {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        margin: 5px 10px 20px;
        font-family: "fredoka one", "Courier New", Courier, monospace;

        .status-task-message {
            color: #41b883ff;
            font-style: oblique;
            font-weight: 900;
        }
    }

    .task-content {
        display: flex;
        flex-direction: column;
        text-align: justify;
        padding: 5px;
        font-family: "ibm plex mono", "Courier New", Courier, monospace;
        font-weight: 300;
    }

}
</style>