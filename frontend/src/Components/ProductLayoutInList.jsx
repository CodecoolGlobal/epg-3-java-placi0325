import { useNavigate } from "react-router-dom";
import "../css/ProductList.css";

const ProductLayoutInList = ({ product }) => {
  const navigate = useNavigate();

  return (
    <div onClick={() => navigate(`/products/${product.id}`)}>
      <style>
        @import
        url('https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap');
      </style>
      <p>{product.name} </p>
      <p>${product.price}</p>
    </div>
  );
};

export default ProductLayoutInList;
