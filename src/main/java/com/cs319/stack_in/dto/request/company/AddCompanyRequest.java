package com.cs319.stack_in.dto.request.company;

import com.cs319.stack_in.entity.IndustryType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * AddCompanyRequest
 *
 * @author Ariunaa Gantumur
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddCompanyRequest {

    @NotNull(message = "{val.not.null}")
    private String name;

    @NotNull(message = "{val.not.null}")
    private String location;

    @NotNull(message = "{val.not.null}")
    private String imagePath;

    @NotNull(message = "{val.not.null}")
    private List<String> industryTypeList;

}
