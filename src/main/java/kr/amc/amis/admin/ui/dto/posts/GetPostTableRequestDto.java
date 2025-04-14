package kr.amc.amis.admin.ui.dto.posts;

import kr.amc.amis.common.domain.Pageable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostTableRequestDto extends Pageable {

    private Long postId;
}
