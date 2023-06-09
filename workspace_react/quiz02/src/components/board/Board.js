
function Board(){

    const posts = [
        { id: 1, author: 'User1', title: 'First Post', date: '2023-01-01', views: 100 },
        { id: 2, author: 'User2', title: 'Second Post', date: '2023-01-02', views: 200 },
        { id: 3, author: 'User3', title: 'Third Post', date: '2023-01-03', views: 150 }
      ];

    return (
        <div>
            <table>
                <thead>
                    <tr>
                        <th>글번호</th>
                        <th>작성자</th>
                        <th>제목</th>
                        <th>작성일</th>
                        <th>조회수</th>
                    </tr>
                </thead>
                <tbody>
                {posts.map(post => (
                    <tr key={post.id}>
                        <td>{post.id}</td>
                        <td>{post.author}</td>
                        <td>{post.title}</td>
                        <td>{post.date}</td>
                        <td>{post.views}</td>
                    </tr>
                    ))
                }
                </tbody>
            </table>
        </div>
    );
};

export default Board;