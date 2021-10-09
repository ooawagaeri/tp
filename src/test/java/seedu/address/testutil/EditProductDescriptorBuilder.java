package seedu.address.testutil;

import seedu.address.logic.commands.products.EditProductCommand.EditProductDescriptor;
import seedu.address.model.products.Description;
import seedu.address.model.products.Manufacturer;
import seedu.address.model.products.Product;
import seedu.address.model.products.ProductName;
import seedu.address.model.products.Type;

public class EditProductDescriptorBuilder {
    private EditProductDescriptor descriptor;

    public EditProductDescriptorBuilder() {
        descriptor = new EditProductDescriptor();
    }

    public EditProductDescriptorBuilder(EditProductDescriptor descriptor) {
        this.descriptor = new EditProductDescriptor(descriptor);
    }

    public EditProductDescriptorBuilder(Product product) {
        descriptor = new EditProductDescriptor();
        descriptor.setProductName(product.getName());
        descriptor.setType(product.getType());
        descriptor.setManufacturer(product.getManufacturer());
        descriptor.setDescription(product.getDescription());
    }

    public EditProductDescriptorBuilder withProductName(String productName) {
        descriptor.setProductName(ProductName.getName(productName));
        return this;
    }

    public EditProductDescriptorBuilder withProductName(ProductName productName) {
        descriptor.setProductName(productName);
        return this;
    }

    public EditProductDescriptorBuilder withType(String type) {
        descriptor.setType(Type.getType(type));
        return this;
    }

    public EditProductDescriptorBuilder withType(Type type) {
        descriptor.setType(type);
        return this;
    }

    public EditProductDescriptorBuilder withManufacturer(String manufacturer) {
        descriptor.setManufacturer(Manufacturer.getManufacturer(manufacturer));
        return this;
    }

    public EditProductDescriptorBuilder withManufacturer(Manufacturer manufacturer) {
        descriptor.setManufacturer(manufacturer);
        return this;
    }

    public EditProductDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(Description.getDescription(description));
        return this;
    }

    public EditProductDescriptorBuilder withDescription(Description description) {
        descriptor.setDescription(description);
        return this;
    }

    public EditProductDescriptor build() {
        return this.descriptor;
    }
}
