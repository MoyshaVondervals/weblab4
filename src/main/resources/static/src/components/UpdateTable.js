import { Table } from "antd";
import React, { useEffect, useState, forwardRef, useImperativeHandle } from "react";
import "../styles/styles.css";
import { useNavigate } from "react-router-dom";
import { useAtom } from "jotai";
import { jwtTokenAtom } from "../redux/store";
import axios from "axios";

const TableDots = forwardRef((props, ref) => {
    const [jwtToken] = useAtom(jwtTokenAtom); // Получаем токен
    const [dataResp, setDataResp] = useState([]); // Состояние для данных таблицы
    const navigate = useNavigate();

    // Вынесенная функция fetchData
    const fetchData = async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/dot", {
                headers: {
                    Authorization: "Bearer " + jwtToken,
                },
            });
            setDataResp(response.data); // Обновляем данные
        } catch (error) {
            console.error("Ошибка при загрузке данных:", error);
            if (error.response?.status === 401) {
                // Если токен истек, перенаправляем на страницу входа
                navigate("/login");
            }
        }
    };

    useEffect(() => {
        fetchData();
    }, [jwtToken, navigate]); // Хук выполняется при изменении `jwtToken` или `navigate`

    useImperativeHandle(ref, () => ({
        refreshTable: fetchData, // Делаем fetchData доступной через ref
    }));

    const columns = [
        {
            title: "X",
            dataIndex: "x",
            key: "x",
        },
        {
            title: "Y",
            dataIndex: "y",
            key: "y",
        },
        {
            title: "R",
            dataIndex: "r",
            key: "r",
        },
        {
            title: "Status",
            dataIndex: "isHit",
            key: "status",
            render: (isHit) => (
                <span style={{ color: isHit ? "green" : "red" }}>
                    {isHit ? "Hit" : "Miss"}
                </span>
            ), // Отображаем "Hit" зеленым, "Miss" красным
        },
    ];

    return (
        <div className="table-container">
            <Table
                dataSource={dataResp}
                columns={columns}
                rowKey={(record) => record.id} // Используем `id` как уникальный идентификатор строки
            />
        </div>
    );
});

export default TableDots;
