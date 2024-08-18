package org.lab.dao.ListWrappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lab.dao.responseModels.OwnerModel;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerListWrapper {
    private List<OwnerModel> owners;
}
