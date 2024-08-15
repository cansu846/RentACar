package kodlama.io.rentACar.business.concretes;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetByIdResponse;
import kodlama.io.rentACar.business.responses.GetaAllBrandsResponse;
import kodlama.io.rentACar.entities.concretes.Brand;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperManager;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.rules.BrandBusinessRules;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BrandManager implements BrandService {

    private ModelMapperManager modelMapperManager;
    BrandRepository brandRepository;
    private BrandBusinessRules brandBusinessRules;

    public BrandManager(BrandRepository brandRepository, ModelMapperManager modelMapperManager) {
        this.brandRepository=brandRepository;
        this.modelMapperManager = modelMapperManager;
    }

    @Override
    public List<GetaAllBrandsResponse> getAll() {

        List<Brand> brands = brandRepository.findAll();
        List<GetaAllBrandsResponse> brandsResponses = new ArrayList<GetaAllBrandsResponse>();

       /* for (Brand brand : brands) {
            //GetaAllBrandsResponse getaAllBrandsResponse = new GetaAllBrandsResponse();
            //getaAllBrandsResponse.setId(brand.getId());
            //getaAllBrandsResponse.setName(brand.getName());
            //responsItem.add(getaAllBrandsResponse);
        }*/

        brandsResponses = brands.stream().map(brand -> this.modelMapperManager.forResponse()
                .map(brand, GetaAllBrandsResponse.class)).collect(Collectors.toList());
        return brandsResponses;
    }

    @Override
    public GetByIdResponse getById(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetByIdResponse getByIdResponse = modelMapperManager.forResponse().map(brand, GetByIdResponse.class);
        return getByIdResponse;
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        //Brand brand = new Brand();
        //brand.setName(createBrandRequest.getName());
        
        this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());

        Brand brand = modelMapperManager.forRequest().map(createBrandRequest, Brand.class);
        this.brandRepository.save(brand);
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        Brand brand = modelMapperManager.forRequest().map(updateBrandRequest, Brand.class);
        this.brandRepository.save(brand);
    }

    @Override
    public void delete(int id) {
        //Brand brand = brandRepository.findById(id).orElseThrow();
        //this.brandRepository.delete(brand);
        this.brandRepository.deleteById(id);
    }
}
