<template>
    <div class="the-head">
        <div class="logos">
            <img src="@/assets/vue-logo.svg" alt="logo-vue">
            <span class="plus-sign">+</span>
            <img src="@/assets/spring-logo.png" alt="spring-vue">
        </div>

        <div class="the-header">
            <h1 class="header-title">Seja bem-vindo ao ToDoJ app</h1 >
            <h3 class="header-subtitle">Criado e mantido por {{ creator }}.</h3>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref } from "vue"
const creator = ref("Fabio Carneiro")
</script>

<style lang="scss" scoped>
@import url("@/assets/styles/fonts.css");

.the-head {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin: 20px auto;
    
    .logos {

        img {
            width: 60px;
        }

        .plus-sign {
            font-family: "fredoka one", Arial, Helvetica, sans-serif;
            font-size: 60px;
            color: #0a0;
            margin: auto 10px;
        }
    }

    .the-header {
        text-align: center;
        margin: 10px auto;

        .header-title {
            font-family: "fredoka one", Arial, Helvetica, sans-serif;
            margin-bottom: -15px;
        }

        .header-subtitle {
            font-family: Jost, Arial, Helvetica, sans-serif;
            font-weight: 200;
            font-size: 1.5em;
        }
    }
}
</style>