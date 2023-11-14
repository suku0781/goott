let test = '';
const result = (text) => {return text};
const options = {
  method: 'GET',
  headers: {
    accept: 'application/json',
    Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMDExOWEyNjliMTEyODIxNzgwMmQ2ZDQ0Njg0Y2RmZSIsInN1YiI6IjY1MzYyYTYwOTFmMGVhMDBmZTFhYzlhZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WSaJyjT7-HZ0yHLDdfs1O9eKc6scsmQFcLWLqnBSP9I'
  }
};

fetch('https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=ko-KR&page=1&sort_by=popularity.desc', options)
  .then(response => response.json())
  .then(response => {
    console.log(response)
    test = response.results[0].backdrop_path;
  })
  .catch(err => console.error(err));

  result(test)