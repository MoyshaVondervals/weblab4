// App.jsx
import "./App.css";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import LoginPage from "./components/LoginPage";
import DashBoard from "./components/DashBoard";
import RegisterPage from "./components/RegisterPage";
import PrivateRoute from "./PrivateRoute";
import React from "react";

function App() {
    const router = createBrowserRouter([
        {
            path: "/",
            element: <LoginPage />,
        },
        {
            path: "/register",
            element: <RegisterPage />,
        },
        {
            path: "/dashboard",
            element: <PrivateRoute element={<DashBoard />} />,
        },
    ]);

    return (
        <div className="App">
            <div className="cap">
                <h1 id="cap">Покалюхин Илья Игоревич</h1>
                <h2>Группа: P3210</h2>
                <p>Вариант: 521992</p>
            </div>
            <RouterProvider router={router} />
        </div>
    );
}

export default App;
