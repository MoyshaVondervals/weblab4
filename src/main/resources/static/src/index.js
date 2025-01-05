import React from "react";
import ReactDOM from "react-dom/client"; // Используем createRoot из React 18
import App from "./App";


const root = ReactDOM.createRoot(document.getElementById("root")); // Создаём корень
root.render(
    <React.StrictMode>
        <App />
    </React.StrictMode>
);
