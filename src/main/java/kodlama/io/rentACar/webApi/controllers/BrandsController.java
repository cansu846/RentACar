package kodlama.io.rentACar.webApi.controllers;

import jakarta.servlet.http.HttpServletResponse;
import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetByIdResponse;
import kodlama.io.rentACar.business.responses.GetaAllBrandsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandsController{

    private final HttpServletResponse httpServletResponse;
    private BrandService brandService;

    @Autowired
    public BrandsController(BrandService brandService, HttpServletResponse httpServletResponse) {
        this.brandService = brandService;
        this.httpServletResponse = httpServletResponse;
    }
    @GetMapping()
    public List<GetaAllBrandsResponse> getAll() {
        return brandService.getAll();
    }

    @GetMapping("{id}")
    public GetByIdResponse getById(@PathVariable int id) {
        return brandService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(code= HttpStatus.CREATED)
    public void add(CreateBrandRequest createBrandRequest){
        this.brandService.add(createBrandRequest);
    }

    @PutMapping()
    public void update(UpdateBrandRequest updateBrandRequest){
        this.brandService.update(updateBrandRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.brandService.delete(id);
    }
}
