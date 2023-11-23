import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { Alert, StyleSheet, Text, View } from 'react-native';
import * as Location from 'expo-location';
import Loading from './components/Loading';
import InternetStore from './components/InternetStore';
import axios from 'axios';

class App extends React.Component {
  state = {
    isLoading: true,
    temp: 10,
    condition: 'Clear',
  };

  async getWeather(location) {
    try {
      await Location.requestPermissionsAsync();
      // const { data } = await axios.get('https://rs-clone-server.herokuapp.com/players/');
      return { data: 'ok' }; // data;
    } catch(error) {
      Alert.alert('Cant get data');
      return {};
    }
  }

  async getLocation() {
    try {
      await Location.requestPermissionsAsync();
      const location = await Location.getCurrentPositionAsync({});
      return { latitude: location.coords.latitude, longitude: location.coords.longitude };
    } catch(error) {
      Alert.alert('Cant get Location');
      return { latitude: null, longitude: null };
    }
  }

  componentDidMount() {
    this.getLocation()
    .then((location) => {
      if (location.latitude && location.longitude) {
        console.log(location.latitude, location.longitude);
        this.getWeather(location).then((weather) => {
          console.log(weather);
        });
        this.setState({ isLoading: false });
      }
    });
  }

  render() {
    const { isLoading, temp, condition } = this.state;
    return (
      isLoading ? <Loading /> : <InternetStore temp={ Math.round(temp) } condition={ condition } />
    );
  }
}

export default App;

/* 
export default function App() {
  return (
    <Loading />
  );
}
*/

/* 
export default function App() {
  return (
    <View style={styles.container}>
      <View style={styles.containerUp}>
        <Text style={styles.text}>Open up App.js to start working on your app!!!</Text>
      </View>
      <View style={styles.containerDown}>
        <Text style={styles.text}>Open up App.js to start working on your app!!!</Text>
      </View>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    // backgroundColor: '#f00',
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  containerUp: {
    flex: 1,
    backgroundColor: '#fff',
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  containerDown: {
    flex: 3,
    backgroundColor: '#ccc',
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  text: {
    color: '#aaa',
    fontSize: 24,
    width: '80%',
  },
}); */
