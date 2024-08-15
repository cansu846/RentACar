package kodlama.io.rentACar.business.abstracts;

import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetByIdResponse;
import kodlama.io.rentACar.business.responses.GetaAllBrandsResponse;

import java.util.List;

public interface BrandService {

    List<GetaAllBrandsResponse> getAll();
    GetByIdResponse getById(int id);
    void add(CreateBrandRequest createBrandRequest);
    void update(UpdateBrandRequest updateBrandRequest);
    void delete(int id);
}
