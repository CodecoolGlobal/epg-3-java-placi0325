import "../css/NewProductForm.css";

const NewProductForm = ({ onCancel, onSave }) => {
  const onSubmit = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entries = [...formData.entries()];

    const product = entries.reduce((acc, entry) => {
      const [k, v] = entry;
      acc[k] = v;
      return acc;
    }, {});

    return onSave(product);
  };

  return (
    <div className="firstdiv">
      <div className="container">
        <form className="product-form" onSubmit={onSubmit}>
          <h1 className="text">Upload the item you want to sell!</h1>
          <div className="form-row">
            <div className="input-data">
              <input name="name" id="name" required />
              <label htmlFor="name">Product name:</label>
              <div className="underline"></div>
            </div>

            <div className="input-data textarea">
              <input name="description" id="description" required></input>
              <label htmlFor="description">Product description:</label>
              <div className="underline"></div>
            </div>

            <div className="input-data">
              <input type="number" name="price" id="price" required />
              <label htmlFor="price">Price:</label>
              <div className="underline"></div>
            </div>
          </div>

          <div className="submit-btn">
            <div className="input-data">
              <input type="submit" className="button" value="Upload" />
              <div className="inner"></div>
            </div>
            <div className="input-data">
              <input
                type="button"
                className="button"
                value="Cancel"
                onClick={onCancel}
              />
              <div className="inner"></div>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default NewProductForm;
