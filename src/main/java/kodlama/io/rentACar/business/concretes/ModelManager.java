package kodlama.io.rentACar.business.concretes;

import kodlama.io.rentACar.business.abstracts.ModelService;
import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.responses.GetAllModelsResponse;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperManager;
import kodlama.io.rentACar.dataAccess.abstracts.ModelRepository;
import kodlama.io.rentACar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ModelManager implements ModelService {

    private ModelRepository modelRepository;
    private ModelMapperManager modelMapperManager;

    @Override
    public List<GetAllModelsResponse> getAll() {

        List<Model> models = modelRepository.findAll();
        List<GetAllModelsResponse> getAllModelsResponses = new ArrayList<GetAllModelsResponse>();

        getAllModelsResponses = models.stream().map(model->this.modelMapperManager.forResponse()
                .map(model, GetAllModelsResponse.class)).collect(Collectors.toList());

        return getAllModelsResponses;
    }

    @Override
    public void add(CreateModelRequest createModelRequest) {

            Model model = modelMapperManager.forRequest().map(createModelRequest, Model.class);
            this.modelRepository.save(model);
        }
    }

