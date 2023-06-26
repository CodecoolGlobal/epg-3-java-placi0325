import { useState } from "react";
import { useNavigate } from "react-router-dom";
import NewProductForm from "../Components/NewProductForm";
import Loading from "./Loading/Loading";
import Footer from "../Components/Footer";

const createProduct = (product) => {
  return fetch("/products", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(product),
  });
};

const NewProductCreator = () => {
  const [loading, setLoading] = useState();
  const navigate = useNavigate();

  const handleCreateProduct = (product) => {
    setLoading(true);

    createProduct(product)
      .then(() => {
        navigate("/products");
      })
      .catch((err) => {
        throw err;
      })
      .finally(() => {
        setLoading(false);
      });
  };

  if (loading) {
    return <Loading />;
  }

  return (
    <div>
      <NewProductForm
        onCancel={() => navigate("/products")}
        onSave={handleCreateProduct}
      />
      <Footer />
    </div>
  );
};

export default NewProductCreator;
