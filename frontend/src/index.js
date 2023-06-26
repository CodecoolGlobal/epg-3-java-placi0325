import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import LandingPage from './Pages/LandingPage';
import reportWebVitals from './reportWebVitals';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import ErrorPage from './Pages/ErrorPage';
import ProductList from './Pages/ProductList';
import SignUpForm from './Pages/SignUpForm';
import LoginForm from './Pages/LoginForm';
import ProductPage from './Pages/ProductPage';
import NewProductCreator from './Pages/NewProductCreator';


const router = createBrowserRouter([
{
  path: "/",
  errorElement: <ErrorPage />,
  children: [
    {
      path: "/",
      element: <LandingPage />
    },
    {
      path: "/products",
      element: <ProductList />
    },
    {
      path: "/signup",
      element: <SignUpForm />
    },
    {
      path: "/login",
      element: <LoginForm />
    },
    {
      path:"/products/:id",
      element:<ProductPage/>
    },
    {
      path: "/newproduct",
      element: <NewProductCreator />
    }
  ]
}
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

reportWebVitals();
