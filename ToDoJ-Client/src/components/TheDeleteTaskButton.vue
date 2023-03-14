<template>
    <div>
        <button @click="deleteATask()" class="delete-button">X</button>
    </div>
</template>

<script setup lang="ts">
import { deleteTask } from "@/assets/ts/api"

const props = defineProps({
    idTask:String
})

const emits = defineEmits({
    updateTaskList:String
})

const deleteATask = async () => {
    await deleteTask(props.idTask??"")
    emits("updateTaskList")
}

</script>

<style scoped lang="scss">
    @import url("@/assets/styles/fonts.css");
    .delete-button {
        display: inline;
        box-shadow: 2px 2px 0 red;
        border: 1px solid red;
        border-radius: 15px;
        background-color: tomato;
        color: white;
        font-size: 20px;
        text-align: center;
        padding: 2px 6px;
        transition: all .3s ease-in-out;

        &:hover {
            transform: scale(1.1);
        }
        
        &:active {
            transform: translate(1px 1px);
        }
    }
</style>