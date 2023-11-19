<script setup>
import { RouterLink } from "vue-router";
</script>

<template>
  <div class="header">
    <nav class="navbar navbar-expand-lg">
      <div class="container-fluid">
        <RouterLink :to="{ name: 'home' }"><a class="navbar-brand" href="#">Triends</a></RouterLink>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                aria-expanded="false">
                Dropdown
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li>
                  <hr class="dropdown-divider" />
                </li>
                <li><a class="dropdown-item" href="#">Something else here</a></li>
              </ul>
            </li>
          </ul>
          <RouterLink :to="{ name: 'member-login' }"><button class="login-button">Login</button></RouterLink><br />
          <RouterLink :to="{ name: 'member-regist' }"><button class="signup-button">Sign up</button></RouterLink><br />
        </div>
      </div>
    </nav>
  </div>
</template>

<style scoped>
.header {
  background-color: #B0D9B1;
}

a {
  text-decoration: none;
}

button {
  margin: 0 10px;
  padding: 4px 10px;
  border-radius: 10%;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}

.login-button {
  background-color: #B0D9B1;
}

.signup-button {
  color: #B0D9B1;
  background-color: black;
  margin-right: 10vw;
}
</style>

