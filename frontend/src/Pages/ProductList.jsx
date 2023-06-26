import { useEffect, useState } from "react";
import Loading from "./Loading/Loading";
import ProductTable from "./ProductTable";
import "../css/ProductList.css"
import Footer from "../Components/Footer";

const fetchProducts = (signal) => {
  return fetch("/products/all", { signal }).then((res) => res.json());
};

const ProductList = () => {
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState(null);
 

  useEffect(() => {
    const controller = new AbortController();

    fetchProducts(controller.signal)
      .then((products) => {
        setLoading(false);
        setData(products);
      })
      .catch((error) => {
        if (error.name !== "AbortError") {
          setData(null);
          throw error;
        }
      });

    return () => controller.abort();
  }, []);

  if (loading) {
    return <Loading />;
  }

  return <div>
    <ProductTable products={data}/>
    <Footer />
  </div>;
};

export default ProductList;
