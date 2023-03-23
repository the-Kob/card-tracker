import React from 'react';
import ReactDOM from 'react-dom/client';
import 'bootstrap/dist/css/bootstrap.min.css';
import reportWebVitals from './reportWebVitals';
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Collections, {loader as collectionsLoader} from "./routes/collections";
import Collection, {loader as collectionLoader, action as collectionAction} from "./routes/collection";
import ErrorPage from "./routes/error-page";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Collections />,
        errorElement: <ErrorPage />,
        loader: collectionsLoader,
    },
    {
        path: "/:collectionId",
        element: <Collection />,
        errorElement: <ErrorPage />,
        loader: collectionLoader,
        action: collectionAction,
    },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
