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
      <div class="container">
        <form className="product-form" onSubmit={onSubmit}>
          <h1 class="text">Upload the item you want to sell!</h1>
          <div class="form-row">
            <div class="input-data">
              <input name="name" id="name" required />
              <label htmlFor="name">Product name:</label>
              <div class="underline"></div>
            </div>

            <div class="input-data textarea">
              <input name="description" id="description" required></input>
              <label htmlFor="description">Product description:</label>
              <div class="underline"></div>
            </div>

            <div class="input-data">
              <input type="number" name="price" id="price" required />
              <label htmlFor="price">Price:</label>
              <div class="underline"></div>
            </div>
          </div>

          <div class="submit-btn">
            <div class="input-data">
              <input type="submit" class="button" value="Upload" />
              <div class="inner"></div>
            </div>
            <div class="input-data">
              <input
                type="button"
                class="button"
                value="Cancel"
                onClick={onCancel}
              />
              <div class="inner"></div>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default NewProductForm;
