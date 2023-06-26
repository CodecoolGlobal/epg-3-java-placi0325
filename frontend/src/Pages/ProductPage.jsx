import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import Loading from "./Loading/Loading";
import "../css/ProductPage.css";

const ProductPage = () => {
  const [loading, setLoading] = useState(true);
  const [product, setProduct] = useState(null);
  const { id } = useParams();

  const navigate = useNavigate();

  const fetchProduct = (signal) => {
    return fetch(`/products/${id}`, { signal }).then((data) => data.json());
  };

  useEffect(() => {
    const controller = new AbortController();
    fetchProduct(controller.signal)
      .then((products) => {
        setLoading(false);
        setProduct(products);
      })
      .catch((error) => {
        if (error.name !== "AbortError") {
          setProduct(null);
          throw error;
        }
      });
    return () => controller.abort();
  }, []);

  if (loading) {
    return <Loading />;
  }
  return (
    <>
      <div className="product">
        <style>
          @import
          url('https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap');
        </style>
        <img className="image" src={product.image} alt="product" />
        <h2 className="info">{product.name}</h2>
        <p className="info">{product.description}</p>
        <p className="info">{product.price} $</p>
        <button className="button" onClick={() => navigate("/products")}>Cancel</button>
      </div>
    </>
  );
};

export default ProductPage;
