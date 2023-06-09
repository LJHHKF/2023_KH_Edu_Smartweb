import Board from '../components/board/Board'
import PagingNavi from '../components/paging/PagingNavi';

function BoardPage(){
    return(
        <div>
            <Board/>
            <PagingNavi />
        </div>
    );
};

export default BoardPage;